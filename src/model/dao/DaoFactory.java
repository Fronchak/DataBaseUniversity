package model.dao;

import db.DB;
import model.dao.impl.UniversityDaoJDBC;

public class DaoFactory {

	public static UniversityDao getUniversityDao() {
		return new UniversityDaoJDBC(DB.getConnection());
	}
	
}
