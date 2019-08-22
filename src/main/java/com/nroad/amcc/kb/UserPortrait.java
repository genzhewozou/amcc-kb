package com.nroad.amcc.kb;

import com.nroad.amcc.support.View.ViewAreaTop3Profession;
import com.nroad.amcc.support.View.ViewGraduateArea;
import com.nroad.amcc.support.View.ViewLastYearScore;
import scala.Int;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserPortrait {

    public List<ViewAreaTop3Profession> viewBestProfessions;

    public CandidateInformation candidateInformation;

//    private Map<Integer, Integer> lastYearScoreMap = new HashMap<>();
//
//    public Map<String, Integer> areaMap;  //专业top1的毕业生去向统计图

    private List<ViewLastYearScore> viewLastYearScores;

    private List<ViewGraduateArea> viewGraduateAreas;

    public double rank;

    public int alumni;

    public List<ViewAreaTop3Profession> getViewBestProfessions() {
        return viewBestProfessions;
    }

    public void setViewBestProfessions(List<ViewAreaTop3Profession> viewBestProfessions) {
        this.viewBestProfessions = viewBestProfessions;
    }

    public CandidateInformation getCandidateInformation() {
        return candidateInformation;
    }

    public void setCandidateInformation(CandidateInformation candidateInformation) {
        this.candidateInformation = candidateInformation;
    }

//    public Map<Integer, Integer> getLastYearScoreMap() {
//        return lastYearScoreMap;
//    }
//
//    public void setLastYearScoreMap(Map<Integer, Integer> lastYearScoreMap) {
//        this.lastYearScoreMap = lastYearScoreMap;
//    }

    public List<ViewLastYearScore> getViewLastYearScores() {
        return viewLastYearScores;
    }

    public void setViewLastYearScores(List<ViewLastYearScore> viewLastYearScores) {
        this.viewLastYearScores = viewLastYearScores;
    }

//    public Map<String, Integer> getAreaMap() {
//        return areaMap;
//    }
//
//    public void setAreaMap(Map<String, Integer> areaMap) {
//        this.areaMap = areaMap;
//    }


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
}
