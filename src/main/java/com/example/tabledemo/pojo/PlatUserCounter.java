package com.example.tabledemo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PlatUserCounter {
  private String id;
  private String deviceId;
  private String userid;
  private String deptid;
  private String total;
  private String copyTotal;
  private String colorTotal;
  private String scanFaxPrintFullColor;
  private String scanFaxScansLarge;
  private String scanFaxPrintFullColorLarge;
  private String scanFaxScans;
  private String scanFaxPrintBlack;
  private String scanFaxPrintLarge;
  private String copyFullColorLarge;
  private String copyTotalLarge;
  private String monochromeTotal;
  private String copy2Color;
  private String copy2ColorLarge;
  private String copySingleColor;
  private String copySingleColorLarge;
  private String copyFullColor;
  private String faxTx;
  private String faxRx;
  private String duplexTotal;
  private String numberOfPrints;
  private String numberOfOriginals;
  private String copyBlack;
  private String copyBlackLarge;
  private String scanFaxPrintBlackLarge;
  private String scanFaxPrint;
  private String sheetsOfPrints;
  private String print2Color;
  private String print2ColorLarge;
  private String printBlack;
  private String printBlackLarge;
  private String printTotal;
  private String printTotalLarge;
  private String printFullColor;
  private String printFullColorLarge;
  private String scanFaxNumberOfPrintsColor;
  private String scanFaxNumberOfPrintsBlack;
  private String scanFaxNumberOfPrints;
  private String printNumberOfPrintsSingle2Color;
  private String printNumberOfPrintsFullColor;
  private String printNumberOfPrintsBlack;
  private String printNumberOfPrints;
  private String copyNumberOfPrintsSingle2Color;
  private String copyNumberOfPrintsFullColor;
  private String copyNumberOfPrintsBlack;
  private String copyNumberOfPrints;
  private String numberOfPrintssingle2Color;
  private String numberOfPrintsBlack;
  private String numberOfPrintsFullColor;
  private String nin1PrintsOther;
  private String nin1Prints4In1;
  private String nin1Prints2In1;
  private String nin1Prints;
  private String numberOfPrintsOtherSize;
  private String numberOfPrintsLongSize;
  private String numberOfPrintsLedgerSize;
  private String numberOfPrintsLegalSize;
  private String numberOfPrintsLetterSize;
  private String numberOfPrintsStatementSize;
  private String numberOfPrintsB5Size;
  private String numberOfPrintsB4Size;
  private String numberOfPrintsA4Size;
  private String numberOfPrintsA3Size;
  private String rectime;
  private String isReport;

  private String userName;
  private String deptName;
  private String deviceName;
  private String deviceIp;
  private Integer parentId;
}