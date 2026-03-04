package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkLowLevel;
import com.revrobotics.spark.SparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ClimbSubsystem extends SubsystemBase {
    private final SparkMax Winch = new SparkMax(Constants.StorageConstants.conveyorID, SparkLowLevel.MotorType.kBrushless);
    private final RelativeEncoder ClimbEncoder = Winch.getEncoder();

    public void WinchUp() {
        if(ClimbEncoder.getPosition() < Constants.ClimbConstants.RelativeEncoder){
            Winch.setVoltage(Constants.ClimbConstants.leftWinchVoltage);
            Winch.set(Constants.ClimbConstants.leftWinchSpeed);
        }
        else{
            WinchStop();
        }
    }
    public void WinchDown() {
        Winch.setVoltage(-Constants.ClimbConstants.leftWinchVoltage);
        Winch.set(-Constants.ClimbConstants.leftWinchSpeed);
    }

    public void WinchStop() {
        Winch.setVoltage(0);

    }





}

