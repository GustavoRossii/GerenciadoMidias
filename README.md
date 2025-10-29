# Sistema de Integração de APIs de Mídia Social (Padrão Adapter)

Este projeto implementa um sistema unificado de gerenciamento de redes sociais utilizando os padrões de design Adapter, Factory Method e Strategy em Java.

## Objetivo

O sistema oferece uma interface única (`GerenciadorMidiaSocial`) para publicar conteúdo em diversas plataformas (Twitter, Instagram, LinkedIn), mesmo que suas APIs subjacentes (simuladas em `br.com.agencia.api`) sejam totalmente heterogêneas.

## Padrões de Design Utilizados

1.  **Adapter Pattern:** O núcleo da solução. As classes `TwitterAdapter`, `InstagramAdapter` e `LinkedInAdapter` implementam a interface `ISocialMediaPlatform` e "traduzem" as chamadas unificadas para os métodos específicos de cada API (`TwitterAPI`, `InstagramAPI`, etc.).
2.  **Factory Method Pattern:** A classe `SocialMediaFactory` fornece um método `createPlatform(String, Map)` que encapsula a lógica de instanciar e configurar o adapter correto com base na plataforma e configurações fornecidas.
3.  **Strategy Pattern:** A interface `ISocialMediaPlatform` atua como a *Strategy*. O `GerenciadorMidiaSocial` (o *Contexto*) opera sobre esta interface, permitindo que a estratégia de publicação seja alterada em tempo de execução sem que o gerenciador precise conhecer detalhes de implementação.

## Arquitetura

* **`service/ISocialMediaPlatform.java` (Target):** A interface unificada que nosso sistema deseja usar.
* **`api/*.java` (Adaptees):** As APIs externas simuladas com métodos e modelos incompatíveis.
* **`adapter/*.java` (Adapters):** As classes que conectam o *Target* aos *Adaptees*.
* **`model/*.java` (Unified Models):** Os DTOs unificados (`Conteudo`, `Publicacao`, `Estatisticas`) que fluem pelo nosso sistema (Task 2).
* **`factory/SocialMediaFactory.java` (Factory):** A classe que constrói os adapters dinamicamente (Task 3).
* **`service/GerenciadorMidiaSocial.java` (Client):** O consumidor final da interface `ISocialMediaPlatform`.

## Diagrama de Classes

```mermaid
classDiagram
    direction BT
    
    class GerenciadorMidiaSocial {
        +agendarPublicacao(ISocialMediaPlatform, Conteudo) Publicacao
    }

    class SocialMediaFactory {
        +createPlatform(String, Map) ISocialMediaPlatform
    }

    class Conteudo
    class Publicacao
    class Estatisticas
    
    GerenciadorMidiaSocial ..> ISocialMediaPlatform : uses
    GerenciadorMidiaSocial ..> Conteudo : uses
    SocialMediaFactory ..> ISocialMediaPlatform : creates
    SocialMediaFactory ..> TwitterAdapter : creates
    SocialMediaFactory ..> InstagramAdapter : creates
    SocialMediaFactory ..> LinkedInAdapter : creates
    
    class ISocialMediaPlatform {
        <<Interface>>
        +postar(Conteudo) Publicacao
        +obterEstatisticas(String) Estatisticas
        +autenticar(String)
    }

    class TwitterAdapter {
        -api: TwitterAPI
        +postar(Conteudo) Publicacao
        +obterEstatisticas(String) Estatisticas
    }
    
    class InstagramAdapter {
        -api: InstagramAPI
        +postar(Conteudo) Publicacao
        +obterEstatisticas(String) Estatisticas
    }
    
    class LinkedInAdapter {
        -api: LinkedInAPI
        +postar(Conteudo) Publicacao
        +obterEstatisticas(String) Estatisticas
    }

    class TwitterAPI {
        <<Adaptee>>
        +tweet(String) String
        +getTweetMetrics(String) String
    }
    
    class InstagramAPI {
        <<Adaptee>>
        +postMedia(String, byte[]) long
        +getPostInsights(long) int[]
    }
    
    class LinkedInAPI {
        <<Adaptee>>
        +submitShare(String) String
        +getShareAnalytics(String) String
    }
    
    ISocialMediaPlatform <|.. TwitterAdapter : implements
    ISocialMediaPlatform <|.. InstagramAdapter : implements
    ISocialMediaPlatform <|.. LinkedInAdapter : implements

    TwitterAdapter ..> TwitterAPI : adapts
    InstagramAdapter ..> InstagramAPI : adapts
    LinkedInAdapter ..> LinkedInAPI : adapts
    
    ISocialMediaPlatform ..> Publicacao : returns
    ISocialMediaPlatform ..> Estatisticas : returns
