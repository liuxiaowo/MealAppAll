package com.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UploadServlet extends HttpServlet {
	java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	java.text.SimpleDateFormat formatdate = new java.text.SimpleDateFormat(
			"yyyy-MM-dd");
	java.util.Date currentTime = new java.util.Date();// �õ���ǰϵͳʱ��

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {

			request.setCharacterEncoding("UTF-8"); // ���ô�����������ı����ʽ
			response.setContentType("text/html;charset=UTF-8"); // ����Content-Type�ֶ�ֵ
			PrintWriter out = response.getWriter();

			// ����Ĵ��뿪ʼʹ��Commons-UploadFile��������ϴ����ļ�����
			FileItemFactory factory = new DiskFileItemFactory(); // ����FileItemFactory����
			ServletFileUpload upload = new ServletFileUpload(factory);
			// �������󣬲��õ��ϴ��ļ���FileItem����
			List<FileItem> items = upload.parseRequest(request);
			String uploadPath = getServletContext().getRealPath("/UploadFile");// �õ���Ӧ���µ�uploadĿ¼�ڷ������ϵľ���·��
			uploadPath += "\\";
			File file = new File(uploadPath);
			if (!file.exists()) {
				file.mkdir();
			}
			System.out.println(uploadPath);
			String filename = ""; // �ϴ��ļ����浽���������ļ���
			InputStream is = null; // ��ǰ�ϴ��ļ���InputStream����
			// ѭ�������ϴ��ļ�
			for (FileItem item : items) {
				// ������ͨ�ı���
				if (item.isFormField()) {
					if (item.getFieldName().equals("filename")) {
						// ������ļ���Ϊ�գ����䱣����filename��
						if (!item.getString().equals(""))
							filename = item.getString("UTF-8");
					}
				}
				// �����ϴ��ļ�
				else if (item.getName() != null && !item.getName().equals("")) {
					// �ӿͻ��˷��͹������ϴ��ļ�·���н�ȡ�ļ���
					filename = item.getName().substring(
							item.getName().lastIndexOf("\\") + 1);
					is = item.getInputStream(); // �õ��ϴ��ļ���InputStream����
				}
			}
			System.out.println(filename);
			UUID uuid = UUID.randomUUID();
			String uuidFileName = uuid
					+ filename.substring(filename.lastIndexOf("."));
			// ��·�����ϴ��ļ�����ϳ������ķ����·��
			filename = uploadPath + uuidFileName;
			System.out.println(filename);
			// ����������Ѿ����ں��ϴ��ļ�ͬ�����ļ����������ʾ��Ϣ
			if (new File(filename).exists()) {
				new File(filename).delete();
			}
			if (!filename.equals("")) {
				// ��FileOutputStream�򿪷���˵��ϴ��ļ�
				FileOutputStream fos = new FileOutputStream(filename);
				byte[] buffer = new byte[8192]; // ÿ�ζ�8K�ֽ�
				int count = 0;
				// ��ʼ��ȡ�ϴ��ļ����ֽڣ����������������˵��ϴ��ļ��������
				while ((count = is.read(buffer)) > 0) {
					fos.write(buffer, 0, count);

				}
				fos.close();
				is.close();
				out.println(uuidFileName);
			}
		} catch (Exception e) {

		}
	}
}
