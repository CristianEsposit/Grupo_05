package modelo;

public abstract class DecoratorViaje implements IViaje,Comparable<IViaje> {
	IViaje iviaje;

	public IViaje getIviaje() {
		return iviaje;
	}

	public void setIviaje(IViaje iviaje) {
		this.iviaje = iviaje;
	}
	

	public Object clone() throws CloneNotSupportedException {
		IViaje clon=(IViaje)super.clone();
		return clon;
	}
	
}
