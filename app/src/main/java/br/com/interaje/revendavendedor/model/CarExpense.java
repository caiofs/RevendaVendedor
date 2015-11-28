package br.com.interaje.revendavendedor.model;

/**
 * Created by caio on 20/11/15.
 */
public class CarExpense {
    private Long id;
    private Long carId;
    private Double value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
