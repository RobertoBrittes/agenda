package br.edu.ifpr.agenda.view;

import br.edu.ifpr.agenda.model.Contato;
import br.edu.ifpr.agenda.model.Endereco;

import br.edu.ifpr.agenda.controller.ContatoController;
public class Main {
    public static void main(String[] args) {
        System.out.println("Testando");
        Contato contato = new Contato();
        Endereco endereco = new Endereco();
        endereco.setRua("Rua dos Bobos");
        endereco.setNumero("zero");
        endereco.setCidade("Cascavel");
        endereco.setEstado("Paraná");

        ContatoController controller = new ContatoController();
        contato.setNome("Fulano de Tal");
        contato.setCelular("(45)9999-9999");
        contato.setEmail("fulano@gmail.com");
        contato.setEndereco(endereco);
        controller.cadastrarContato(contato);

        controller.deleteBDA();
    } 
}