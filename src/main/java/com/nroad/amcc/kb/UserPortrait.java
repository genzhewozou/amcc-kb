package com.nroad.amcc.kb;

import com.nroad.amcc.support.View.ViewAreaTop3Profession;
import com.nroad.amcc.support.View.ViewGraduateArea;
import com.nroad.amcc.support.View.ViewLastYearScore;

import java.util.List;

public class UserPortrait {

    public List<ViewAreaTop3Profession> viewBestProfessions;

    private List<ViewLastYearScore> viewLastYearScores;

    private List<ViewGraduateArea> viewGraduateAreas;

    public double rank;

    public int alumni;

    public CandidateInformation candidateInformation;

    public List<ViewAreaTop3Profession> getViewBestProfessions() {
        return viewBestProfessions;
    }

    public void setViewBestProfessions(List<ViewAreaTop3Profession> viewBestProfessions) {
        this.viewBestProfessions = viewBestProfessions;
    }

    public List<ViewLastYearScore> getViewLastYearScores() {
        return viewLastYearScores;
    }

    public void setViewLastYearScores(List<ViewLastYearScore> viewLastYearScores) {
        this.viewLastYearScores = viewLastYearScores;
    }

    public List<ViewGraduateArea> getViewGraduateAreas() {
        return viewGraduateAreas;
    }

    public void setViewGraduateAreas(List<ViewGraduateArea> viewGraduateAreas) {
        this.viewGraduateAreas = viewGraduateAreas;
    }

    public double getRank() {
        return rank;
    }

    public void setRank(double rank) {
        this.rank = rank;
    }

    public int getAlumni() {
        return alumni;
    }

    public void setAlumni(int alumni) {
        this.alumni = alumni;
    }

    public CandidateInformation getCandidateInformation() {
        return candidateInformation;
    }

    public void setCandidateInformation(CandidateInformation candidateInformation) {
        this.candidateInformation = candidateInformation;
    }
}
