package kr.co.infopub.chapter.s199.test;
import java.sql.SQLException;
import java.util.List;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import kr.co.infopub.chapter.s200.client.EmployeeService;
import kr.co.infopub.chapter.s200.dto.DepCount;
import kr.co.infopub.chapter.s200.util.EmpUtil;
import kr.co.infopub.chapter.s200.util.PTS;
import javafx.scene.chart.PieChart;
// s188
public class DepChartFx extends Application {
 int total=0;   // ��ü ��� ��
 private ObservableList<Data> getChartData(List<DepCount> dlists) {
    ObservableList<Data> answer = FXCollections.observableArrayList();
    for (DepCount dc: dlists) {
    	// �μ��̸�(�μ����̵�), �μ��� ��
    	 answer.add(new PieChart.Data( dc.getDepartment_name()
    			   +"("+dc.getDepartment_id()+")", dc.getCount()  ));
	}
    return answer;
 }
 @Override
 public void start(Stage stage) {
  try {
	// DAO, Convert�� ���μ� ����ϰ� ���
	EmployeeService service=EmployeeService.getInstance();
    List<DepCount> dlists =service.findAllDepCounts();
    // �μ��� �ο��� ���ؼ� ��ü ������� ���Ѵ�.
    for(DepCount dc: dlists){
	    total+=dc.getCount();
    }
    Scene scene = new Scene(new Group());
    stage.setTitle("�μ��� �ο��� " +PTS.toDay());
    stage.setWidth(750);
    stage.setHeight(800);
	
	PieChart pieChart = new PieChart();
	pieChart.setTitle("�μ��� �ο��� ��"+total+"��");
	// ������Ʈ��  ObservableList�� �����Ѵ�
	// �μ��� �μ��� ���� ���� ���̸� �����Ѵ�.
    pieChart.setData(getChartData(dlists));
    pieChart.setClockwise(true); 
    pieChart.setStartAngle(180);  
    pieChart.setLabelsVisible(true); 
    //������
    //pieChart.setLabelLineLength(20);
    //pieChart.setLegendSide(Side.LEFT);
    pieChart.setPrefWidth(750);
    pieChart.setPrefHeight(700); 
    final Label caption = new Label("");
    String value=
	    	   "-fx-font-size: 25px;           "
	    	  +"-fx-font-family: 'Arial Black';    ";
    caption.setStyle(value);
    for (final PieChart.Data data : pieChart.getData()) {
        data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
            e-> {
                caption.setTranslateX(e.getSceneX());  // ���콺�� ���� X��ġ��
                caption.setTranslateY(e.getSceneY());  // ���콺�� ���� Y��ġ��
                String sft=String.format("%s %.2f%%(%.0f��)", 
                		EmpUtil.dep(data.getName()),   // �μ��̸�
                		100*data.getPieValue()/total,  // �μ��ο� %����
                		data.getPieValue());           // �μ��ο�
                caption.setText(sft);
            });
    }
    // ������Ʈ�� �̺�Ʈ �߻��� ���� ����(��)
    ((Group) scene.getRoot()).getChildren().addAll(pieChart,caption);
    stage.setScene(scene);
    stage.show();
  } catch (SQLException e) {
	System.out.println(e);
  }
 }
 public static void main(String[] args) {
  launch(args);
 }
}