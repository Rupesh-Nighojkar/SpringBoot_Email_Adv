package com.nextwave.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailModel {

    private String toEmail[];

    private String ccEmail[];

    private String bccEmail[];


    private String emailSubject;

    private String emailBody;

    private String emailAttachment;
}
