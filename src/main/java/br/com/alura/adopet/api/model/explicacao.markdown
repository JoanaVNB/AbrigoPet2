O propósito deste vídeo foi direcionar nossa atenção para os relacionamentos entre entidades e suas práticas recomendadas, bem como as convenções da JPA. As modificações realizadas na entidade Adoção não envolveram grandes mudanças no código; apenas ajustamos alguns atributos e o código permaneceu inalterado.

No entanto, essas mudanças representaram uma melhoria voltada para o aprimoramento de desempenho. Assim, quanto menos dados desnecessários carregarmos no banco de dados, mais rápida será a nossa consulta.

O ideal é modificar esse comportamento para que seja lazy (tardio). Dessa forma, ao carregarmos a adoção, as tabelas de tutor e pet não serão carregadas automaticamente, sendo carregadas apenas sob demanda quando forem realmente necessárias e não de forma antecipada.

Para indicarmos isso para a JPA, na indicação do relacionamento @ManyToOne abrimos e fechamos parênteses passando o atributo fetch, que será igual a FetchType.LAZY.

Ao digitar "FetchType", surgem as opções "eager" e "lazy". Todo vínculo ToOne é, por padrão, "eager". Isso significa que quando carregamos a entidade principal, esse relacionamento é carregado simultaneamente. Mas como não queremos que isso aconteça, optamos pelo lazy. Fazemos a mesma coisa no relacionamento do pet.
o adicionarmos o atributo fetch não estamos informando para nunca mais carregar o tutor e o pet, mas sim quando for solicitado.