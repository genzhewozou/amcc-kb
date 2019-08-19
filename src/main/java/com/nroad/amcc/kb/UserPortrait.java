package com.nroad.amcc.kb;

import com.nroad.amcc.support.View.ViewAreaTop3Profession;

import java.util.List;

public class UserPortrait {

    public List<ViewAreaTop3Profession> viewAreaTop3Professions;  //区域Top3专业

    public List<ViewAreaTop3Profession> studentsCheckProfessions;  //学生选择的专业

    public List<ViewAreaTop3Profession> getViewAreaTop3Professions() {
        return viewAreaTop3Professions;
    }

    public void setViewAreaTop3Professions(List<ViewAreaTop3Profession> viewAreaTop3Professions) {
        this.viewAreaTop3Professions = viewAreaTop3Professions;
    }

    public List<ViewAreaTop3Profession> getStudentsCheckProfessions() {
        return studentsCheckProfessions;
    }

    public void setStudentsCheckProfessions(List<ViewAreaTop3Profession> studentsCheckProfessions) {
        this.studentsCheckProfessions = studentsCheckProfessions;
    }
}
