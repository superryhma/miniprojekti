package models;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "reference")
@DiscriminatorValue("article")
public class Article extends Reference {
	
	@Column(name = "author")
	protected String author;
	@Column(name = "title")
	protected String title;
	@Column(name = "journal")
	protected String journal;
	@Column(name = "year")
	protected Integer year;
	@Column(name = "volume")
	protected Integer volume;
	@Column(name = "number")
	protected Integer number;
	@Column(name = "pages")
	protected String pages;
	@Column(name = "month")
	protected String month;
	@Column(name = "note")
	protected String note;
	@Column(name = "key")
	protected String key;
}