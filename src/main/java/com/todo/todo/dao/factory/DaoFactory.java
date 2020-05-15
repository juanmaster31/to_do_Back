package com.todo.todo.dao.factory;

import com.todo.todo.dao.IBackendDAO;
import com.todo.todo.dao.impl.BackendDAO;

public class DaoFactory {
	private DaoFactory() {
		
	}
	
	public static IBackendDAO create() {
		return BackendDAO.getInstance();		
	}

}
