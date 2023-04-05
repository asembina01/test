package com.example.eotinish.service;

import com.example.eotinish.entity.Appeal;
import com.example.eotinish.dto.req.AppealResponse;
import com.example.eotinish.dto.req.Content;
import com.vdurmont.emoji.EmojiParser;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EotinishMapper {

    public String toTgMessage(Appeal appeal){
        StringBuilder sb = new StringBuilder();
        sb.append(":bangbang: Обращение :bangbang:");
        sb.append(" (");
        sb.append(appeal.getAppealType());
        sb.append(") ");
        if(appeal.getApplicantType().equals("COMPANY")){
            sb.append("юр лица ");
            sb.append(appeal.getCompanyName());
            sb.append(" (");
            sb.append(appeal.getCompanyHeadFIO());
            sb.append(") ");
        } else {
            sb.append("физ лица ");
            sb.append(" (");
            sb.append(appeal.getPersonFIO());
            sb.append(") ");
        }
        sb.append(appeal.getRegNumber());

        sb.append(" от");
        sb.append(appeal.getStartDate());
        sb.append("\n\n");
        sb.append("Содержание: \n\n");
        sb.append(appeal.getAppealData());
        sb.append("\n\n");
        sb.append("Срок до ");
        sb.append(appeal.getDeadline());
        sb.append("\n");
        if(appeal.getDeadlineDays()<=4){
            sb.append(":sos: :sos: :sos:");
        }
        sb.append("Осталось дней ");
        sb.append(appeal.getDeadlineDays());
        return EmojiParser.parseToUnicode(sb.toString());

    }

    public List<Appeal> toEntityList(AppealResponse response){
        if(response.getTotalElements()==0){
            return Collections.emptyList();
        }
        List<Appeal> dtoList = response.getContent().stream().map(content -> toEntity(content)).collect(Collectors.toList());
        return dtoList;

    }

    private Appeal toEntity(Content content){
        Appeal appeal = new Appeal();
        appeal.setRegNumber(content.getRegNumber());
        appeal.setCurrentState(content.getCurrentState());
        appeal.setStartDate(content.getStartDate());
        appeal.setDeadline(content.getDeadline());
        appeal.setOrganizationNameRu(content.getOrganization().getNameRu());
        appeal.setMainExecutor(content.getExecutors().get(0).getName());
        appeal.setDeadlineDays(content.getDeadlineDays());
        appeal.setAppealType(content.getApplication().getType().getNameRu());
        appeal.setAppealData(content.getApplication().getData());
        appeal.setApplicantType(content.getApplication().getApplicant().getApplicantType());
        if(content.getApplication().getApplicant().getPersonSecondName()==null){
            appeal.setPersonFIO(content.getApplication().getApplicant().getPersonSecondName()+" "+content.getApplication().getApplicant().getPersonFirstName()+" "+content.getApplication().getApplicant().getPersonMiddleName());
        } else{
            appeal.setPersonFIO("-");
        }
        if(content.getApplication().getApplicant().getPersonLawAddress()==null && content.getApplication().getApplicant().getPersonFactAddress()==null){
            appeal.setPersonAddress("-");
        } else {
            appeal.setPersonAddress(content.getApplication().getApplicant().getPersonFactAddress()+" / "+content.getApplication().getApplicant().getPersonLawAddress());
        }
        appeal.setCompanyName(content.getApplication().getApplicant().getCompanyName());
        appeal.setCompanyHeadFIO(content.getApplication().getApplicant().getCompanyHeadSecondName()+" "+content.getApplication().getApplicant().getCompanyHeadFirstName()+" "+content.getApplication().getApplicant().getCompanyHeadMiddleName());
        appeal.setCompanyAddress(content.getApplication().getApplicant().companyFactAddress+"/"+content.getApplication().getApplicant().getCompanyLawAddress());
        return appeal;
    }


}
