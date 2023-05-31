/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.controlador;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.logging.Level;
import java.util.logging.Logger;
import sv.edu.udb.Conexion;

/**
 *
 * @author Alexander-Siguenza
 */
public class checkLoginClass {

    public checkLoginClass() {
    }
    
    /*
    CREATE DEFINER=`root`@`localhost` PROCEDURE `verifyUser`(IN pusuario TEXT, IN ppassword TEXT, OUT outCout INT)
    READS SQL DATA
   BEGIN  
    SELECT count(*) INTO outCout FROM usuarios WHERE usuario  = pusuario and password = ppassword;    
    END
    */

    public static int verifyUser(String correo, String clave) {
        int val = 0;
        try {
            Connection con = Conexion.Conectar();
            CallableStatement cmst = con.prepareCall("CALL verifyUser(?,?,?)");
            cmst.setString(1, correo);
            cmst.setString(2, clave);
            cmst.registerOutParameter(3, Types.VARCHAR); // para recuperarparametros de salidad de MYSQL -> OUT outCout INT          
            cmst.executeUpdate();
            val = cmst.getInt(3);
            con.close();          
        } catch (SQLException sqlex) {
            System.out.println(sqlex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return val;
    }

}