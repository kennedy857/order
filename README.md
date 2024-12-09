# ORDER
Projeto em formato POC para API's de gerencia de pedidos.

## Instalação
Passos para instalar e configurar o ambiente de desenvolvimento:
- Java 21
- Lombok necessario
- Banco Postgres ( base de nome: gerped usuario: postgres senha:1234 ) configurada no arquivo application-dev.properties
- rodar o seguinte script para para inserir produtos na base :
  INSERT INTO public.produto
  (valor_unitario, codigo, descricao)
  VALUES(10.00, 1, 'Prod 1');
  INSERT INTO public.produto
  (valor_unitario, codigo, descricao)
  VALUES(20.00, 2, 'Prod 2');
