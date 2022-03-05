package model.dao;

import db.DB;
import model.dao.impl.UniversityDaojdbc;

public class DaoFactory {

	public static UniversityDao getUniversityDao() {
		return new UniversityDaojdbc(DB.getConnection());
	}
	
}
