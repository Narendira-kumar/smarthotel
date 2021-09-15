package com.demo.hp.ws.service.impl;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.demo.hp.ws.component.EmailService;
import com.demo.hp.ws.dto.TransactionDto;
import com.demo.hp.ws.dto.UserEmailDto;
import com.demo.hp.ws.entity.Transaction;
import com.demo.hp.ws.exception.ConstantMessages;
import com.demo.hp.ws.exception.EntityNotFoundException;
import com.demo.hp.ws.exception.ErrorMessages;
import com.demo.hp.ws.repository.TransactionRepository;
import com.demo.hp.ws.service1.TransactionService;

import io.github.resilience4j.retry.annotation.Retry;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private Environment env;

	@Autowired
	@Qualifier("emailsender")
	private EmailService emailService;

	@Autowired
	TransactionRepository transactionRepository;

	@Autowired
	RabbitTemplate rabbitTemplate;

	private final static String pattern = "dd/MM/yyyy";

	@Override
	 
	public Transaction createTransaction(TransactionDto transactionDto) {

		Transaction trans = new Transaction();

		if (transactionDto.getTransactionId() != null) {
			Transaction existingTrans = transactionRepository.findByTransactionId(transactionDto.getTransactionId());

			if (transactionDto.getUserId() != null) {

				String forObject1 = new RestTemplate().getForObject(env.getProperty("USEREMAIL_URL") + "checkuser/{id}",
						String.class, transactionDto.getUserId());
				System.out.println(forObject1.toString());

				if (forObject1.equals("FALIURE")) {
					throw new EntityNotFoundException(ErrorMessages.NO_USER_RECORD_FOUND.getErrorMessage());
				}
			}

			if (null != existingTrans) {
				trans.setTransactionId(existingTrans.getTransactionId());

			}
		}
		trans.setUserId(transactionDto.getUserId());
		trans.setTransactionType(transactionDto.getTransactionType());
		trans.setBillAmount(transactionDto.getBillAmount());
		transactionRepository.save(trans);

		return trans;
	}

	@Override
	 
	public String userReport(long id) {

		if (id > 0) {

			String forObject1 = new RestTemplate().getForObject(env.getProperty("USEREMAIL_URL") + "checkuser/{id}",
					String.class, id);

			if (forObject1.equals("FALIURE"))
				throw new EntityNotFoundException(ErrorMessages.NO_USER_RECORD_FOUND.getErrorMessage());

			List<Transaction> trans = transactionRepository.findByUserId(id);
			if (trans.get(0).getTransactionId() != null) {
				//
				try {
					XSSFWorkbook workbook = new XSSFWorkbook();

					XSSFCellStyle cellSty = workbook.createCellStyle();
					XSSFCellStyle cellSty1 = workbook.createCellStyle();
					XSSFFont font = workbook.createFont();
					XSSFFont font1 = workbook.createFont();
					font.setBold(true);
					font.setFontHeightInPoints((short) 10);
					cellSty.setFont(font);

					font1.setBold(true);
					font1.setFontHeightInPoints((short) 15);
					cellSty1.setAlignment(HorizontalAlignment.CENTER);
					cellSty1.setFillForegroundColor(new XSSFColor(Color.BLUE).getIndexed());
					cellSty1.setFont(font1);

					XSSFSheet sheet = workbook.createSheet("User Data");// creating a blank sheet
					FileOutputStream out = new FileOutputStream(new File(env.getProperty("FILENAMEPATH")));

					Row header1 = sheet.createRow(0);
					header1.createCell(0).setCellValue("Smart Hotel Report");
					header1.getCell(0).setCellStyle(cellSty1);

					int firstRow = 0;
					int lastRow = 0;
					int firstCol = 0;
					int lastCol = 5;
					sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, firstCol, lastCol));

					Row header = sheet.createRow(1);

					header.createCell(0).setCellValue("UserId");
					header.getCell(0).setCellStyle(cellSty);
					header.createCell(1).setCellValue("TransactionId");
					header.getCell(1).setCellStyle(cellSty);
					header.createCell(2).setCellValue("TransactionType");
					header.getCell(2).setCellStyle(cellSty);
					header.createCell(3).setCellValue("Amount");
					header.getCell(3).setCellStyle(cellSty);
					header.createCell(4).setCellValue("Date");
					header.getCell(4).setCellStyle(cellSty);
					header.createCell(5).setCellValue("TransactionStatus");
					header.getCell(5).setCellStyle(cellSty);

					int rownum = 2;
					for (Transaction transaction1 : trans) {
						Row row = sheet.createRow(rownum++);
						createList(transaction1, row);

					}

					workbook.write(out);
					out.close();

				} catch (Exception e) {
					e.printStackTrace();
				}

				UserEmailDto user = new UserEmailDto();

				UserEmailDto forObject = new RestTemplate()
						.getForObject(env.getProperty("USEREMAIL_URL") + "userEmail/{id}", UserEmailDto.class, id);

				String emailid = forObject.getEmail();
				String name = forObject.getFirstName();
				String surname = forObject.getLastName();

				emailService.sendmail(name, surname, emailid);
			} else
				throw new EntityNotFoundException(ErrorMessages.NO_TRANSACTION_RECORD_FOUND.getErrorMessage());

		}
		return ConstantMessages.REPORT_EMAIL;
	}

	private void createList(Transaction transaction1, Row row) {

		Cell cell = row.createCell(0);
		cell.setCellValue(transaction1.getUserId());

		cell = row.createCell(1);
		cell.setCellValue(transaction1.getTransactionId());

		cell = row.createCell(2);
		cell.setCellValue(transaction1.getTransactionType());

		cell = row.createCell(3);
		cell.setCellValue(transaction1.getBillAmount());

		cell = row.createCell(4);

		cell.setCellValue(getFormatedDateAsString(transaction1.getTransactionDate()));

		cell = row.createCell(5);
		cell.setCellValue(transaction1.getTransactionStatus());
	}

	public static String getFormatedDateAsString(java.util.Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		String formattedDate = null;
		try {
			formattedDate = sdf.format(date);
		} catch (Exception ex) {
		}
		return formattedDate;
	}

	@Override
	 
	public String updateTransaction(long userid) {

		String response = "";
		if (userid > 0) {

			String forObject1 = new RestTemplate().getForObject(env.getProperty("USEREMAIL_URL") + "checkuser/{id}",
					String.class, userid);

			if (forObject1.equals("FALIURE"))
				throw new EntityNotFoundException(ErrorMessages.NO_USER_RECORD_FOUND.getErrorMessage());

			List<Transaction> trans = transactionRepository.findByUserId(userid);

			for (Transaction trans1 : trans) {
				trans1.setTransactionStatus("SETTLED");
				transactionRepository.save(trans1);
			}

			// Send the report to user
			response = userReport(userid);

			// Update the Room as avaliable

			String msg1 = Long.toString(userid);

			rabbitTemplate.convertAndSend("room-book", "roombook", msg1.toString().getBytes());

			// Update the userRoom -remove room id
			// Update the userRoom -remove room id
			rabbitTemplate.convertAndSend("room-vocate", "userroom", msg1.toString().getBytes());
		}
		return response + ' ' + ConstantMessages.GREETING;
	}

	 

}