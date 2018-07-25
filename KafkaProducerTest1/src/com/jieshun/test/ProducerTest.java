package com.jieshun.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.wb.swt.SWTResourceManager;

import com.jieshun.util.DateUtils;
import com.jieshun.util.FileUtil;
import com.jieshun.util.JsonUtil;
import com.jieshun.util.StringUtil;
import com.jieshun.vo.TXDataObject;

public class ProducerTest {

	protected Shell shlKafkaproducer;
	private String topic;
	private String bootstrapSeverStr;
	private String parkCodeStr;
	private String sendParkInfoStr;
	private String sendParkInStr;
	private String sendWaitStr;
	private String sendParkOutStr;
	private String parkOrderStr;
	private static CountDownLatch mCountDownLatch = new CountDownLatch(3);
	public static KafkaProducer<String, String> producer = null;
	private String sendCountStr;
	private Display display;
	private Text textContent;
	private boolean isTopic;
	private Combo textTopic;
	private String content;
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ProducerTest window = new ProducerTest();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		display = Display.getDefault();
		createContents();
		shlKafkaproducer.open();
		shlKafkaproducer.layout();
		while (!shlKafkaproducer.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlKafkaproducer = new Shell();
		shlKafkaproducer.setImage(SWTResourceManager.getImage("image/producer.ico"));
		shlKafkaproducer.setSize(704, 601);
		shlKafkaproducer.setText("KafKaProducer");

		Label lblTopic = new Label(shlKafkaproducer, SWT.NONE);
		lblTopic.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		lblTopic.setBounds(86, 95, 46, 23);
		lblTopic.setText("Topic:");

		Label lblBootstrapservers = new Label(shlKafkaproducer, SWT.NONE);
		lblBootstrapservers.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		lblBootstrapservers.setBounds(2, 52, 135, 23);
		lblBootstrapservers.setText("bootstrap.servers:");

		Text bootstrap = new Text(shlKafkaproducer, SWT.BORDER);
		bootstrap.setText("119.23.42.209:9092");
		bootstrap.setBounds(138, 52, 168, 23);

		Label parkCodeLabel = new Label(shlKafkaproducer, SWT.NONE);
		parkCodeLabel.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		parkCodeLabel.setBounds(55, 142, 83, 23);
		parkCodeLabel.setText("park.code:");

		Text parkCode = new Text(shlKafkaproducer, SWT.BORDER);
		parkCode.setText("Test123");
		parkCode.setBounds(138, 142, 168, 23);

		Label lblSendparkinfo = new Label(shlKafkaproducer, SWT.NONE);
		lblSendparkinfo.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		lblSendparkinfo.setBounds(345, 52, 114, 23);
		lblSendparkinfo.setText("send.park.info:");

		Combo sendParkInfo = new Combo(shlKafkaproducer, SWT.NONE);
		sendParkInfo.setItems(new String[] { "true", "false" });
		sendParkInfo.setBounds(465, 52, 126, 25);
		sendParkInfo.setText("false");

		Label lblSendparkin = new Label(shlKafkaproducer, SWT.NONE);
		lblSendparkin.setText("send.park.in:");
		lblSendparkin.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		lblSendparkin.setBounds(358, 95, 97, 23);

		Label lblSendparkout = new Label(shlKafkaproducer, SWT.NONE);
		lblSendparkout.setText("send.park.out:");
		lblSendparkout.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		lblSendparkout.setBounds(345, 124, 114, 23);

		Combo sendParkIn = new Combo(shlKafkaproducer, SWT.NONE);
		sendParkIn.setItems(new String[] { "true", "false" });
		sendParkIn.setBounds(462, 95, 130, 25);
		sendParkIn.setText("false");

		Combo sendParkOut = new Combo(shlKafkaproducer, SWT.NONE);
		sendParkOut.setItems(new String[] { "true", "false" });
		sendParkOut.setBounds(462, 126, 130, 25);
		sendParkOut.setText("false");
		Label countLabel = new Label(shlKafkaproducer, SWT.NONE);
		countLabel.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		countLabel.setBounds(7, 185, 130, 23);
		countLabel.setText("send.total.count:");

		Combo parkOrderCom = new Combo(shlKafkaproducer, SWT.NONE);
		parkOrderCom.setItems(new String[] { "true", "false" });
		parkOrderCom.setBounds(462, 157, 130, 25);
		formToolkit.adapt(parkOrderCom);
		formToolkit.paintBordersFor(parkOrderCom);
		parkOrderCom.setText("false");

		Text sendCount = new Text(shlKafkaproducer, SWT.BORDER);
		sendCount.setText("1");
		sendCount.setBounds(138, 185, 168, 23);

		Label lblSendwait = new Label(shlKafkaproducer, SWT.NONE);
		lblSendwait.setText("send.wait:");
		lblSendwait.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		lblSendwait.setBounds(381, 185, 78, 23);

		Combo sendWait = new Combo(shlKafkaproducer, SWT.NONE);
		sendWait.setItems(new String[] { "true", "false" });
		sendWait.setBounds(462, 185, 130, 25);
		sendWait.setText("false");
		Button btntopic = new Button(shlKafkaproducer, SWT.RADIO);
		btntopic.setSelection(true);
		btntopic.setBounds(54, 10, 97, 23);
		btntopic.setText("功能测试Topic");

		Button btnRadioButton = new Button(shlKafkaproducer, SWT.RADIO);
		btnRadioButton.setBounds(343, 10, 97, 23);
		btnRadioButton.setText("性能测试Topic");

		textContent = new Text(shlKafkaproducer, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI);
		textContent.setBackground(SWTResourceManager.getColor(204, 255, 204));
		textContent.setBounds(25, 260, 614, 293);

		Button btnNewButton = new Button(shlKafkaproducer, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// 参数初始化,解决线程安全问题
				bootstrapSeverStr = bootstrap.getText();
				parkCodeStr = parkCode.getText();
				sendParkInfoStr = sendParkInfo.getText();
				sendParkInStr = sendParkIn.getText();
				sendParkOutStr = sendParkOut.getText();
				parkOrderStr = parkOrderCom.getText();
				sendWaitStr = sendWait.getText();
				sendCountStr = sendCount.getText();
				isTopic = btntopic.getSelection();
				topic = textTopic.getText();
				content = textContent.getText();

				// 处理数据
				new Thread() {

					@Override
					public void run() {

						process();
					}
				}.start();

				// btnNewButton.setEnabled(false);

			}
		});
		btnNewButton.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.BOLD));
		btnNewButton.setBounds(509, 6, 145, 40);
		btnNewButton.setText("发   送");

		Label label = new Label(shlKafkaproducer, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.BOLD));
		label.setBounds(25, 230, 114, 23);
		label.setText("输入发送的数据：");

		textTopic = new Combo(shlKafkaproducer, SWT.NONE);
		textTopic.setItems(
				new String[] {"mb.park.info", "mb.park.in", "mb.park.out", "mb.park.order", "mb.park.space", "msg.tel","msg.wx","msg.zfb","msg.app"});
		textTopic.setBounds(138, 95, 168, 25);
		formToolkit.adapt(textTopic);
		formToolkit.paintBordersFor(textTopic);
		textTopic.setText("test");

		Label park_order_label = new Label(shlKafkaproducer, SWT.NONE);
		park_order_label.setText("send.park.order:");
		park_order_label.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		park_order_label.setBounds(332, 157, 126, 23);
		formToolkit.adapt(park_order_label, true, true);
		
		Button btnjson = new Button(shlKafkaproducer, SWT.NONE);
		btnjson.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				textContent.setText(JsonUtil.formaterAsJSON(textContent.getText()));
			}
		});
		btnjson.setBounds(144, 227, 83, 27);
		formToolkit.adapt(btnjson, true, true);
		btnjson.setText("格式化JSON");
		
		Button button = new Button(shlKafkaproducer, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				textContent.setText("");
			}
		});
		button.setBounds(252, 227, 80, 27);
		formToolkit.adapt(button, true, true);
		button.setText("清除数据");
		btnNewButton.forceFocus();

	}

	public void process() {

		try {

			Properties props = new Properties();
			props.put("bootstrap.servers", bootstrapSeverStr);
			props.put("retries", Integer.valueOf(0));
			props.put("linger.ms", Integer.valueOf(0));
			props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
			props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
			props.put("partitioner.class", "com.jieshun.test.MyPartition");
			producer = new KafkaProducer<String, String>(props);
			if (isTopic) {
				sendData(topic, content, 1);
			} else {

				if ("true".equals(sendParkInfoStr)) {
					sendData("mb.park.info", getParkInfo("mb.park.info"), 1);
					System.out.println("----send mb.park.info over  !");
				}
				if ("true".equals(sendParkInStr)) {
					// 交给入场线程处理
					new Thread() {
						@Override
						public void run() {
							try {
								//耗时统计
								long start = System.currentTimeMillis();
								for (int i = 0; i < Integer.valueOf(sendCountStr).intValue(); i++) {
									sendData("mb.park.in", getParkIn("mb.park.in"), i + 1);
									if ("true".equals(sendWaitStr)) {
										Thread.sleep(100L);
									}
								}
								long end = System.currentTimeMillis();
								System.out.println("----send mb.park.in over----共发送数据："+Integer.valueOf(sendCountStr).intValue()+"条，耗时："+(end-start)+"毫秒");
								
							} catch (Exception e) {
								e.printStackTrace();
							}
							mCountDownLatch.countDown();
						}
					}.start();
				}
				if ("true".equals(sendParkOutStr)) {
					// 交给出场线程处理
					new Thread() {
						@Override
						public void run() {

							try {
								for (int i = 0; i < Integer.valueOf(sendCountStr); i++) {
									sendData("mb.park.out", getParkOut("mb.park.out"), i + 1);
									if ("true".equals(sendWaitStr)) {
										Thread.sleep(100L);
									}
								}
								System.out.println("----send mb.park.out over  !");
							} catch (Exception e) {
								e.printStackTrace();
							}
							mCountDownLatch.countDown();
						}
					}.start();

				}
				if ("true".equals(parkOrderStr)) {
					// 交给订单线程处理
					new Thread() {
						@Override
						public void run() {

							try {

								for (int i = 0; i < Integer.valueOf(sendCountStr); i++) {
									sendData("mb.park.order", getParkOrder("mb.park.order"), i + 1);
									if ("true".equals(sendWaitStr)) {
										Thread.sleep(100L);
									}
								}
								
								System.out.println("----send mb.park.order over  !");
								
							} catch (Exception e) {
								e.printStackTrace();
							}
							mCountDownLatch.countDown();
						}
					}.start();

				}
				mCountDownLatch.await();
				
				producer.flush();
				producer.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (producer != null) {
				producer.close();
			}
		}

	}

	public void sendData(String topic, String content, int index) throws InterruptedException, ExecutionException {
		ProducerRecord<String, String> record = new ProducerRecord<String, String>(topic, content);
		producer.send(record);
		System.out.println("[" + DateUtils.getCurrDateTimeStr() + "]-----send:" + index + ",topic:" + topic);
	}

	public String getParkInfo(String serviceId) {
		TXDataObject txt = new TXDataObject();
		txt.setAttribute("itemId", StringUtil.getUUID());
		txt.setAttribute("parkCode", parkCodeStr);
		txt.setAttribute("parkName", "测试");
		txt.setAttribute("totalSpace", "100");
		txt.setAttribute("inSpace", StringUtil.get4RandomNum());
		txt.setAttribute("telephone", "13511111");
		txt.setAttribute("address", "深圳" + StringUtil.get4RandomNum());
		txt.setAttribute("longitude", "10.33665");
		txt.setAttribute("latitude", "10.33665");
		txt.setAttribute("chargeStandard", "dsffffffffffffffffffsdf");
		txt.setAttribute("status", "NORMAL");
		txt.setAttribute("orderSpace", StringUtil.get4RandomNum());
		txt.setAttribute("bookSpace", StringUtil.get4RandomNum());
		txt.setAttribute("isLock", "1");
		txt.setAttribute("htirdParkCode", "13");
		txt.setAttribute("remark", StringUtil.getUUID());
		String content = JsonUtil.toJson(txt.getAttributes());
		return content;
	}

	public String getParkIn(String serviceId, String id) {
		TXDataObject txt = new TXDataObject();
		txt.setAttribute("itemId", id);
		txt.setAttribute("parkCode", parkCodeStr);
		txt.setAttribute("parkName", "测试");
		txt.setAttribute("carNumber", FileUtil.getCarNumber());
		txt.setAttribute("equipCode", "A000" + StringUtil.get4RandomNum());
		txt.setAttribute("equipName", "设备" + StringUtil.get4RandomNum());
		txt.setAttribute("idno", "A-" + StringUtil.get4RandomNum());
		txt.setAttribute("inOperator", "张山" + StringUtil.getRandomNum(2));
		txt.setAttribute("inTime", DateUtils.getCurrDateTimeStr());
		txt.setAttribute("inTotal", StringUtil.getRandomNum(5));
		String content = JsonUtil.toJson(txt.getAttributes());
		return content;
	}

	
	public String getParkOrder(String serviceId) {
		Map<String, Object> txt = new HashMap<>();
		txt.put("itemId", StringUtil.getUUID());
		txt.put("parkCode", parkCodeStr);
		txt.put("parkName", "test");
		txt.put("carNumber", FileUtil.getCarNumber());
		txt.put("businesserCode", "test" + StringUtil.get4RandomNum());
		txt.put("businesserName", "测试" + StringUtil.getRandomNum(8));
		txt.put("orderNo", StringUtil.getUUID());
		txt.put("transactionId", StringUtil.getUUID());
		txt.put("orderCreateTime", DateUtils.getCurrDateTimeStr());
		txt.put("tradeStatus", "0");
		txt.put("tradeTime", DateUtils.getCurrDateTimeStr());
		txt.put("payType", "2");
		txt.put("inTime", DateUtils.getCurrDateTimeStr());
		txt.put("serviceFeeTime", "150");
		txt.put("thirdUserId", "test2088512674513969");
		txt.put("orderType", "DK");
		txt.put("thirdParkId", "PI1510292363953283630");
		txt.put("parkId", StringUtil.getUUID());
		txt.put("attachType", "2");
		txt.put("totalFee", 50);
		txt.put("channelCode", "test2018");

		String content = JsonUtil.toJson(txt);
		return content;
	}

	public String getParkIn(String serviceId) {
		TXDataObject txt = new TXDataObject();
		txt.setAttribute("itemId", StringUtil.getUUID());
		txt.setAttribute("parkCode", parkCodeStr);
		txt.setAttribute("parkName", "测试");
		//txt.setAttribute("carNumber", FileUtil.getCarNumber());
		txt.setAttribute("carNumber", "粤-B"+StringUtil.getRandomNum(6));
		txt.setAttribute("equipCode", "A000" + StringUtil.get4RandomNum());
		txt.setAttribute("equipName", "设备" + StringUtil.get4RandomNum());
		txt.setAttribute("idno", "A-" + StringUtil.get4RandomNum());
		txt.setAttribute("inOperator", "张山" + StringUtil.getRandomNum(2));
		txt.setAttribute("inTime", DateUtils.getCurrDateTimeStr());
		txt.setAttribute("inTotal", StringUtil.getRandomNum(5));
		String content = JsonUtil.toJson(txt.getAttributes());
		return content;
	}

	public String getTelMsg() {
		Map<String, Object> txt = new HashMap<String, Object>();
		txt.put("itemId", StringUtil.getUUID());
		txt.put("carNumber", "粤-A" + StringUtil.get4RandomNum());
		txt.put("eventItemId",  StringUtil.getUUID());
		txt.put("eventCode", "mb.park.in");
		txt.put("msgType", 0);
		txt.put("pushTime", DateUtils.getCurrDateTimeStr());
		txt.put("productCode", "JPARK");
		txt.put("mobile", "13100000000");
		txt.put("content","content:"+StringUtil.getUUID());
		String content = JsonUtil.toJson(txt);
		return content;
	}
	
	public String getParkOut(String serviceId) {
		TXDataObject txt = new TXDataObject();
		txt.setAttribute("itemId", StringUtil.getUUID());
		txt.setAttribute("parkCode", parkCodeStr);
		txt.setAttribute("parkName", "测试");
		txt.setAttribute("carNumber", FileUtil.getCarNumber());
		txt.setAttribute("freeMoney", "10.2");
		txt.setAttribute("hgMoney", "6.2");
		txt.setAttribute("idno", "Ab" + StringUtil.get4RandomNum());
		txt.setAttribute("inEquipCode", "A" + StringUtil.getRandomNum(8));
		txt.setAttribute("inEquipName", "设备" + StringUtil.getRandomNum(8));
		txt.setAttribute("inTime", DateUtils.getCurrDateTimeStr());
		txt.setAttribute("inTotal", StringUtil.getRandomNum(3));
		txt.setAttribute("outEquipCode", "B" + StringUtil.getRandomNum(8));
		txt.setAttribute("outMode", "3df");
		txt.setAttribute("outOperator", "测试");
		txt.setAttribute("outTime", DateUtils.getCurrDateTimeStr());
		txt.setAttribute("payTypeName", "现金");
		txt.setAttribute("ssMoney", "5454.9");
		txt.setAttribute("yhMoney", "545.4");
		txt.setAttribute("ysMoney", "55.2");
		String content = JsonUtil.toJson(txt.getAttributes());
		return content;
	}
}
