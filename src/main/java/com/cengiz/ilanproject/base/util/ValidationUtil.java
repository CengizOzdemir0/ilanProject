package com.cengiz.ilanproject.base.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtil {
  private static final Pattern VALID_CEP_TELEFON_REGEX = Pattern.compile(
      "[5]{1}[0-9]{9}");

  private ValidationUtil() {
    throw new IllegalStateException("ValidationUtil class");
  }

  public static Boolean tcNoValidation(Long tcno) {
    if (tcno < 10000000000L || tcno > 99999999999L)
      return false;
    long tmp;
    long tmp1;
    int oddsum;
    int evensum;
    int total;
    int chkdigit1;
    long chkdigit2;
    int[] d = new int[9];

    tmp = (long) Math.floor(tcno / 100D);
    tmp1 = (long) Math.floor(tcno / 100D);

    for (int i = 8; 0 <= i; i--) {
      d[i] = (int) tmp1 % 10;
      tmp1 = (long) Math.floor(tmp1 / 10D);
    } // for

    oddsum = d[8] + d[6] + d[4] + d[2] + d[0];
    evensum = d[7] + d[5] + d[3] + d[1];

    total = oddsum * 3 + evensum;
    chkdigit1 = (10 - (total % 10)) % 10;

    oddsum = chkdigit1 + d[7] + d[5] + d[3] + d[1];
    evensum = d[8] + d[6] + d[4] + d[2] + d[0];

    total = oddsum * 3 + evensum;
    chkdigit2 = (10 - (total % 10)) % 10;

    tmp = tmp * 100 + chkdigit1 * 10 + chkdigit2;

    return tmp == tcno;
  }

  public static boolean cepTelefonValidation(Long cepTelefon) {
    if (cepTelefon != null) {
      Matcher matcher = VALID_CEP_TELEFON_REGEX.matcher(cepTelefon.toString());

      if (matcher.matches()) {
        return true;
      }
    }
    return false;
  }
}
