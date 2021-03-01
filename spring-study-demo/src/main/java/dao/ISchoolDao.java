package dao;

import domain.School;

public interface ISchoolDao {

    School querySchoolInfoById(Long treeId);
}
