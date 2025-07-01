📱 API de Rede Social Simplificada
Uma API REST para posts e comentários, desenvolvida com Spring Boot (Java) e H2, perfeita para estudos ou como base para projetos mais complexos.

🛠 Tecnologias
Backend: Java 21 + Spring Boot 3

Banco de Dados: H2

Autenticação: Headers customizados (X-Usuario-Id) (pronto para evoluir para JWT)

Testes: Postman (coleção inclusa)

🌟 Funcionalidades
✅ Posts

📌 Endpoints Principais
Método	Endpoint	Descrição
POST	/api/posts	Criar novo post
GET	/api/posts/{id}	Buscar post por ID
POST	/api/posts/{id}/curtir	Curtir post

Criar, editar, listar e deletar posts
✅ Comentários

Adicionar e gerenciar comentários em posts
✅ Interações

Curtir posts e comentários
