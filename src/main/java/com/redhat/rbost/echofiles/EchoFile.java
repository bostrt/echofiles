package com.redhat.rbost.echofiles;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class EchoFile
 */
@WebServlet("/echo")
public class EchoFile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(EchoFile.class.getSimpleName());

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EchoFile() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			ServletFileUpload upload = new ServletFileUpload();

			FileItemIterator iter = upload.getItemIterator(request);

			while (iter.hasNext()) {
				FileItemStream item = iter.next();
				String name = item.getFieldName();
				InputStream stream = item.openStream();

				String filename = item.getName();

				response.setContentType("application/octet-stream");
				response.setHeader("Content-Disposition", "attachment;filename=" + filename + ".html");

				byte[] buffer = new byte['x'];
				int count = 0;
				int n = 0;
				while (-1 != (n = stream.read(buffer))) {
					log.warning("======");
					response.getOutputStream().write(buffer, 0, n);
					log.warning("buffer.length: " + buffer.length);
					log.warning("bytes read: " + n);
					log.warning("======");
					count += n;
				}
				log.warning("Finished reading, flushing buffer. Total " + count + " bytes.");
				stream.close();
				response.flushBuffer();
			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

}
