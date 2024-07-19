package com.emis.EMIS.services;

import com.emis.EMIS.models.AcademicProgressTrackerEntity;
import com.emis.EMIS.models.LearningStageEntity;
import com.emis.EMIS.models.LevelsEntity;
import com.emis.EMIS.models.StudentEntity;
import com.emis.EMIS.utils.Utilities;
import com.emis.EMIS.wrappers.responseDTOs.ResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AcademicProgressTrackerService {
    private final DataService dataService;
    private final Utilities utilities;

    public ResponseDTO PromoteStudents() {
        for (AcademicProgressTrackerEntity academicProgressTrackerEntity : dataService.findDistinctStudentId()){
            AcademicProgressTrackerEntity academicProgressTracker = new AcademicProgressTrackerEntity();
            int currentTerm = academicProgressTrackerEntity.getTerm();
           if (currentTerm == 3){
               LearningStageEntity learningStage = dataService.findByLearningStageId(academicProgressTrackerEntity.getLearningStageId())
                       .orElseThrow(()->new RuntimeException("LearningStageId not found"));
             LearningStageEntity modifiedLearningStage = dataService.findByLearningStageId(academicProgressTrackerEntity.getLearningStageId() +1)
                       .orElse(learningStage);
               academicProgressTracker.setLearningStageId(modifiedLearningStage.getLearningStageId());
               int currentYear = academicProgressTrackerEntity.getYear();
               academicProgressTracker.setYear(currentYear +1);
           }else {
               academicProgressTracker.setTerm(currentTerm +1);
           }
            academicProgressTracker.setStudentId(academicProgressTrackerEntity.getStudentId());
            academicProgressTracker.setStreamId(academicProgressTrackerEntity.getStreamId());
           dataService.saveProgress(academicProgressTracker);
        }
        return utilities.successResponse("Promoted students",null);
    }
}
