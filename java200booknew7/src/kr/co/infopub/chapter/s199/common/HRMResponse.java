package kr.co.infopub.chapter.s199.common;
import java.util.*;
import java.io.*;
public class HRMResponse extends Vector implements Serializable {
   private int status = -1;
   public HRMResponse () {
      super(1,1);
   }
   // ������ �� ��������, ���- ����, 0���� ���� 
   public int getStatus () {
      return status;
   }
   public void setStatus(int value) {
      status = value;
   }
   public int getNumRows () {
      return this.size();//������ ũ��
   }
}