package vn.com.luanvan.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "uiusecase")
public class UIUsecase {
	private UIUsecaseId id;
	private UI ui;
	private Usecase usecase;
	
	public UIUsecase(){}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "usecaseid", column = @Column(name = "USECASEID", nullable = false)),
			@AttributeOverride(name = "uiid", column = @Column(name = "uiid", nullable = false)) })
	public UIUsecaseId getId() {
		return id;
	}

	public void setId(UIUsecaseId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "uiid", nullable = false, insertable = false, updatable = false)
	public UI getUi() {
		return ui;
	}

	public void setUi(UI ui) {
		this.ui = ui;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "usecaseid", nullable = false, insertable = false, updatable = false)
	public Usecase getUsecase() {
		return usecase;
	}

	public void setUsecase(Usecase usecase) {
		this.usecase = usecase;
	}
	
}
