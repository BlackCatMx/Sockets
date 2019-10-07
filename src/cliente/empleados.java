/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.io.Serializable;

/**
 *
 * @author Posgrado1
 */

public class empleados implements Serializable {
    
    int clave;
    String nombre;
    float sueldo, dias_laborados, total;

    public empleados( int clave, String nombre, float sueldo, float dias_laborados,float total){
        this.clave= clave;
        this.nombre =nombre;
        this.sueldo=  sueldo;
        this.dias_laborados= dias_laborados;
        this.total= total;
    }

    public int getClave() {
        return clave;
    }

    public String getNombre() {
        return nombre;
    }

    public float getSueldo() {
        return sueldo;
    }

    public float getDias_laborados() {
        return dias_laborados;
    }

    public float getTotal() {
        return total;
    }

}
