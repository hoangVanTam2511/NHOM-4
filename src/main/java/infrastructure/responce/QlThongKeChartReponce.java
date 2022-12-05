/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package infrastructure.responce;

import java.util.Date;
import javax.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Admin
 */
// tạo dư xliệu để import aoo chart
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class QlThongKeChartReponce {

    @Column(name = "so_luong")
    private int soLuong;

    @Column(name = "ngay_tao")
    private String ngayTao;

}
