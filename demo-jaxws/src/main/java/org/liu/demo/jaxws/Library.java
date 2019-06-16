package org.liu.demo.jaxws;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService(targetNamespace = "http://library.mycompany.com")
public class Library {
	private static List<Book> bookList = new ArrayList<Book>();
	private static int currentId = 0;

	public Library() {
	}

	@WebMethod
	@WebResult(name = "id")
	public int addRawBook(@WebParam(name = "name") String name,
			@WebParam(name = "author") String author) {
		Book book = new Book();
		book.setId(++currentId);
		book.setName(name);
		book.setAuthor(author);
		bookList.add(book);
		return currentId;
	}

	@WebMethod
	@WebResult(name = "rawBook")
	public String getRawBook(@WebParam(name = "id") int id) {
		Book goal = null;
		for (Book b : bookList) {
			if (id == b.getId()) {
				goal = b;
				break;
			}
		}
		StringBuilder result = new StringBuilder();
		if (goal == null) {
			result.append("No Book Found");
		} else {
			result.append("[id=").append(goal.getId()).append(";name=")
					.append(goal.getName()).append(";author=")
					.append(goal.getAuthor());
		}
		return result.toString();
	}

	@WebMethod
	public void deleteBook(@WebParam(name = "id") int id)
			throws LibraryException {
		Book goal = null;
		for (Book b : bookList) {
			if (id == b.getId()) {
				goal = b;
				break;
			}
		}
		if (goal == null) {
			throw new LibraryException("Fail to delete", "Id not exist.");
		} else {
			bookList.remove(goal);
		}
	}
}