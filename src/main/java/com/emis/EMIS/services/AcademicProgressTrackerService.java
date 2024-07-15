package com.emis.EMIS.services;

import com.emis.EMIS.models.AcademicProgressTrackerEntity;
import com.emis.EMIS.models.LearningStageEntity;
import com.emis.EMIS.models.LevelsEntity;
import com.emis.EMIS.models.StudentEntity;
import com.emis.EMIS.utils.Utilities;
import com.emis.EMIS.wrappers.requestDTOs.AcademicTrackerDTO;
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

//    public ResponseDTO PromoteStudentsToNextTerm(AcademicTrackerDTO academicTrackerDTO) {
//        AcademicProgressTrackerEntity academicProgressTracker = new AcademicProgressTrackerEntity();
//        for (AcademicProgressTrackerEntity academicProgressTrackerEntity : dataService.findDistinctStudentId()){
//           int currentTerm = academicProgressTrackerEntity.getTerm();
//           if (currentTerm > 3){
//               academicProgressTrackerEntity.setLearningStageId(academicProgressTrackerEntity.getLearningStageId() +1);
//           }else {
//               academicProgressTrackerEntity.setTerm(currentTerm +1);
//           }
//           dataService.saveProgress(academicProgressTracker);
//        }
////        for (AcademicProgressTrackerEntity academicProgressTrackerEntity : dataService.findDistinctStudentId()){
////            String currentLearningStage = STR."\{academicProgressTrackerEntity.getLearningStageId()}";
////            List<LearningStageEntity> availableLearningStages = dataService.findLearningStagesByLevel(currentLearningStage);
////            int currentIndex = availableLearningStages.indexOf(currentLearningStage);
////            if (currentIndex != -1 && currentIndex < availableLearningStages.size() - 1){
////                String nextLearningStage = String.valueOf((availableLearningStages.get(currentIndex + 1)));
////                academicProgressTrackerEntity.setLearningStageId(Integer.parseInt(nextLearningStage));
////
////            }
////            dataService.saveProgress(academicProgressTracker);
////        }
//        return utilities.successResponse("Promoted a student to a term",null);
//    }
}
