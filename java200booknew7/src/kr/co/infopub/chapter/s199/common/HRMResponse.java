package kr.co.infopub.chapter.s199.common;
import java.util.*;
import java.io.*;
public class HRMResponse extends Vector implements Serializable {
   private int status = -1;
   public HRMResponse () {
      super(1,1);
   }
   // 응답할 때 성공여부, 양수- 성공, 0이하 실패 
   public int getStatus () {
      return status;
   }
   public void setStatus(int value) {
      status = value;
   }
   public int getNumRows () {
      return this.size();//벡터의 크기
   }
}