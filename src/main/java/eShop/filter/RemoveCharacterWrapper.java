package eShop.filter;

import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class RemoveCharacterWrapper extends HttpServletResponseWrapper{
	private final CharArrayWriter out;
	
	public RemoveCharacterWrapper(HttpServletResponse resp) {
		super(resp);
		out = new CharArrayWriter();
	}

	@Override
	public PrintWriter getWriter() throws IOException {
		return new PrintWriter(out);
	}

	@Override
	public String toString() {
		return out.toString().replaceAll("[\n\t\r]", "");
	}
	
}
