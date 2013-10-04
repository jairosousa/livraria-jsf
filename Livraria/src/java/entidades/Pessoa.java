package entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
// A importação abaixo é para o banco PostgreSQL
//import javax.persistence.SequenceGenerator;

@Entity
// A linha abaixo deve ser usada no caso do banco ser PostgreSQL
//@SequenceGenerator(name= "pessoa_regpessoa_seq", allocationSize = 1, sequenceName = "pessoa_regpessoa_seq")
public abstract class Pessoa extends Entidade implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // A linha acima deve ser substituída pelas linhas comentadas abaixo,
    // no caso do banco ser PostgreSQL
    // @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="pessoa_regpessoa_seq")
    // @Column(columnDefinition = "BIGSERIAL")
    private Integer regPessoa;
    @Column(length=50)
    private String nome;
    @Column(length=50)
    private String sobrenome;

    public Pessoa() {
    }

    public Pessoa(String nome, String sobrenome) {
        this.nome = nome;
        this.sobrenome = sobrenome;
    }

    public Integer getReg() {
        return regPessoa;
    }

    public void setReg(Integer reg) {
        this.regPessoa = reg;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    @Override
    public Serializable getId() {
        return regPessoa;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + (this.regPessoa != null ? this.regPessoa.hashCode() : 0);
        hash = 67 * hash + (this.nome != null ? this.nome.hashCode() : 0);
        hash = 67 * hash + (this.sobrenome != null ? this.sobrenome.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pessoa other = (Pessoa) obj;
        if (this.regPessoa != other.regPessoa && (this.regPessoa == null || !this.regPessoa.equals(other.regPessoa))) {
            return false;
        }
        if ((this.nome == null) ? (other.nome != null) : !this.nome.equals(other.nome)) {
            return false;
        }
        if ((this.sobrenome == null) ? (other.sobrenome != null) : !this.sobrenome.equals(other.sobrenome)) {
            return false;
        }
        return true;
    }

}
