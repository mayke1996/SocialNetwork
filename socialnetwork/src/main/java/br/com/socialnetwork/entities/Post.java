package br.com.socialnetwork.entities;

import java.awt.Image;
import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "post")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Post implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "usuario_id")
	@OneToOne(fetch = FetchType.LAZY)
	private Long userId;

	@Column(name = "comentario_id")
	@OneToMany(fetch = FetchType.LAZY)
	private List<Comment> commentsId;

	@Column(name = "texto")
	private String text;

	@Column(name = "imagem")
	private Image image;

	@Column(name = "link")
	private String link;

}
