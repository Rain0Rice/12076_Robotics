package org.firstinspires.ftc.teamcode;
//Call Packages
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.robotcore.external.hardware.camera.BuiltinCameraDirection;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import com.qualcomm.robotcore.hardware.CRServo;

@TeleOp(name = "Jave_Code")
public class Jave_Code extends LinearOpMode{
	//Call servos/motors variables for the future
	private DcMotor _00_motor;
	private DcMotor _01_motor;
	private DcMotor shoulder_0;
	private DcMotor shoulder_1;
	private DcMotor Arm;
	private CRServo claew;
	private CRServo Spinnery_tingey;
	float LeftPower = 0;
	float RightPower = 0;
	String micro = "";
	//Use the webcam on the robot
	//private static final boolean USE_WEBCAM = true;
	//Variables for apriltag & visionportal
	//private AprilTagProcessor aprilTag;
	//private VisionPortal visionPortal;

	@Override

	public void runOpMode(){
		//Map our hardware on the robot to the variables previously created
		 _00_motor = hardwareMap.get(DcMotor.class, "00_motor");
		 _01_motor = hardwareMap.get(DcMotor.class, "01_motor");
		 shoulder_0 = hardwareMap.get(DcMotor.class, "shoulder_0");
		 shoulder_1 = hardwareMap.get(DcMotor.class, "shoulder_1");
		 Arm = hardwareMap.get(DcMotor.class, "Arm");
		 claew = hardwareMap.get(CRServo.class, "claew");
		 Spinnery_tingey = hardwareMap.get(CRServo.class, "Spinnery_tingey");
		//April Tags(ERRORS, FIX)
		//telemetryAprilTag();

		 //Call Telemetry to show numbers on the robot(QOL)
		 telemetry.update();

		 waitForStart();
		 //Set any motor flips needed
		_01_motor.setDirection(DcMotor.Direction.REVERSE);
		if (opModeIsActive()); {
			while (opModeIsActive()) {
				float LeftPower = gamepad1.left_stick_y;
				float RightPower = gamepad1.right_stick_y;

				//Movement for gamepad1(Trenton)
				_00_motor.setPower(gamepad1.right_stick_y);
				_01_motor.setPower(gamepad1.left_stick_y);
				micro = "none";
				if (gamepad1.right_stick_y == 0 && gamepad1.left_stick_y == 0);
					if (gamepad2.dpad_left == true){ //microadjust the robot left
						_00_motor.setPower(0.5);
						_01_motor.setPower(-0.5);
						micro = "L";
						_00_motor.setPower(0);
						_01_motor.setPower(0);
					}
					if (gamepad2.dpad_right == true){ //microadjust the robot right
						_00_motor.setPower(-0.5);
						_01_motor.setPower(0.5);
						micro = "R";
						_00_motor.setPower(0);
						_01_motor.setPower(0);
					}

				//Arm control for gamepad2(Kelie)
				shoulder_0.setPower(gamepad2.left_stick_y);
				shoulder_1.setPower(-(gamepad2.left_stick_y));
				Arm.setPower(gamepad2.right_stick_y);
				if (gamepad2.dpad_up) {
					Spinnery_tingey.setPower(1);
				}
				if (gamepad2.dpad_down) {
					Spinnery_tingey.setPower(-1);
				}
				if (gamepad2.b == true) {
					shoulder_0.setPower(-0.25);
					shoulder_1.setPower(0.25);
				}

				if (gamepad2.a == true) {
					claew.setPower(-0.15);
					telemetry.addData("Claw", "Open");
				}
				else {
					claew.setPower(0);
					telemetry.addData("Claw", "Closed");
				}



				//Telementry calls
				telemetry.addData("Left Motor", LeftPower); //The speed the robot is trying to move on the Left Motor
				telemetry.addData("Right Motor", RightPower); //The speed the robot is trying to move on the Right Motor
				telemetry.addData("micro", micro);
				telemetry.update();
		  }
		}
	}
}
