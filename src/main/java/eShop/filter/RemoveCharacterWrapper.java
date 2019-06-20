package eShop.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class RemoveCharacterWrapper extends HttpServletResponseWrapper{
	private final StringWriter out;
	
	public RemoveCharacterWrapper(HttpServletResponse resp) {
		super(resp);
		out = new StringWriter();
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
