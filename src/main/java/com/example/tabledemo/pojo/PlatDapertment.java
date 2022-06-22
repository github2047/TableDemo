package com.example.tabledemo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PlatDapertment {
  private long id;
  private String departName;
  private String levels;
  private String encoding;
  private String chargStandard;
  private String quota;
  private String isUse;
  private String companyId;
  private String isFiliale;
  private String sortCode;
  private long parentId;
  private String isReport;
  private long departmentId;
  private long confId;
  private String wxConfId;
  private String otherConfType;
  private String otherDeptId;
  private String otherParentId;
  private String otherConfId;
}
