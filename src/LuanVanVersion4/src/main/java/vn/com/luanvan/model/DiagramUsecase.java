package vn.com.luanvan.model;

// default package
// Generated Jan 31, 2015 7:09:10 PM by Hibernate Tools 4.3.1

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Phanloai generated by hbm2java
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "diagramusecase", catalog = "luanvan")
public class DiagramUsecase implements java.io.Serializable {

	private DiagramUsecaseId id;
	private Usecase usecase;
	private Diagram diagram;

	public DiagramUsecase() {
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "usecaseid", column = @Column(name = "USECASEID", nullable = false)),
			@AttributeOverride(name = "diagramid", column = @Column(name = "DIAGRAMID", nullable = false)) })
	public DiagramUsecaseId getId() {
		return this.id;
	}

	public void setId(DiagramUsecaseId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "USECASEID", nullable = false, insertable = false, updatable = false)
	public Usecase getUsecase() {
		return this.usecase;
	}

	public void setUsecase(Usecase usecase) {
		this.usecase = usecase;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "DIAGRAMID", nullable = false, insertable = false, updatable = false)
	public Diagram getDiagram() {
		return this.diagram;
	}

	public void setDiagram(Diagram diagram) {
		this.diagram = diagram;
	}
}
