package frc.robot.subsystems;

import com.revrobotics.spark.SparkLowLevel;
import com.revrobotics.spark.SparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ClimbSubsystem extends SubsystemBase {
    private final SparkMax leftWinch = new SparkMax(Constants.StorageConstants.conveyorID, SparkLowLevel.MotorType.kBrushless);
    private final SparkMax rightWinch = new SparkMax(Constants.StorageConstants.conveyorID, SparkLowLevel.MotorType.kBrushless);

    public void leftWinchUp() {
        leftWinch.setVoltage(Constants.ClimbConstants.leftWinchVoltage);
    }
    public void leftWinchDown() {
        leftWinch.setVoltage(-Constants.ClimbConstants.leftWinchVoltage);
    }

    public void leftWinchStop() {
        leftWinch.setVoltage(0);
    }



    public void rightWinchUp() {
        leftWinch.setVoltage(Constants.ClimbConstants.rightWinchVoltage);
    }
    public void rightWinchDown() {
        leftWinch.setVoltage(-Constants.ClimbConstants.rightWinchVoltage);
    }

    public void rightWinchStop() {
        rightWinch.setVoltage(0);
    }

}

