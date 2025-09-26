{
  description = "🌌 High Valley Wake PBC - Galactic Infrastructure";
  
  inputs = {
    nixpkgs.url = "github:NixOS/nixpkgs/nixos-unstable";
    clj-nix.url = "github:jlesquembre/clj-nix";
    flake-utils.url = "github:numtide/flake-utils";
  };

  outputs = { self, nixpkgs, clj-nix, flake-utils }:
    flake-utils.lib.eachDefaultSystem (system:
      let
        pkgs = nixpkgs.legacyPackages.${system};
        clj-nix-lib = clj-nix.lib.${system};
        
        # 🌌 Galactic Infrastructure Package
        galactic-infrastructure = clj-nix-lib.buildCljApp {
          projectSrc = ./.;
          name = "highvalleywake-pbc-galactic";
          main-ns = "site.core";
          jdk = pkgs.jdk17;
          buildInputs = with pkgs; [ nodejs npm ];
        };
        
        # 📚 Markdown Wiki Generator
        markdown-wiki = clj-nix-lib.buildCljApp {
          projectSrc = ./.;
          name = "markdown-wiki-generator";
          main-ns = "markdown.wiki";
          jdk = pkgs.jdk17;
        };
        
        # 🧠 AI Blueprint Generator
        ai-blueprint-generator = clj-nix-lib.buildCljApp {
          projectSrc = ./.;
          name = "ai-blueprint-generator";
          main-ns = "ai.blueprints";
          jdk = pkgs.jdk17;
        };
        
        # 🐳 Container Image
        container = pkgs.dockerTools.buildLayeredImage {
          name = "highvalleywake-pbc-galactic";
          tag = "latest";
          contents = [ galactic-infrastructure ];
          config = {
            Cmd = [ "${galactic-infrastructure}/bin/highvalleywake-pbc-galactic" ];
            ExposedPorts = { "8080/tcp" = {}; };
            Env = [
              "NIXOS_VERSION=${self.rev or "dirty"}"
            ];
          };
        };
        
      in
      {
        packages = {
          default = galactic-infrastructure;
          galactic-infrastructure = galactic-infrastructure;
          markdown-wiki = markdown-wiki;
          ai-blueprint-generator = ai-blueprint-generator;
          container = container;
        };
        
        devShells.default = pkgs.mkShell {
          buildInputs = with pkgs; [
            # 🌌 Core Development Tools
            jdk17
            nodejs
            npm
            git
            
            # 🧬 ClojureScript Development
            clojure
            babashka
            clj-kondo
            
            # 🏗️ Build Tools
            gnumake
            gcc
            
            # 📚 Documentation
            pandoc
            texlive.combined.scheme-full
            
            # 🎨 Frontend Development
            nodePackages.svelte
            nodePackages.vite
            nodePackages.typescript
            
            # 🌍 Deployment
            docker
            docker-compose
          ];
          
          shellHook = ''
            echo "🌌 Welcome to High Valley Wake PBC - Galactic Infrastructure"
            echo "🎯 Philosophy: 'Simple made easy, farms made functional'"
            echo "🚀 Vision: Post-industrial ecological civilization"
            echo ""
            echo "Available commands:"
            echo "  bb markdown:scan          - Scan markdown files"
            echo "  bb markdown:parse         - Parse markdown to DSL"
            echo "  bb ai:generate-blueprints - Generate AI blueprints"
            echo "  bb build:all-localhost    - Build for localhost"
            echo "  bb build:all-github-pages - Build for GitHub Pages"
            echo "  bb health:check           - System health check"
            echo ""
            echo "🌱 Ready for galactic infrastructure development!"
          '';
        };
        
        # 🏗️ Build System
        apps = {
          default = flake-utils.lib.mkApp {
            drv = galactic-infrastructure;
          };
          
          markdown-wiki = flake-utils.lib.mkApp {
            drv = markdown-wiki;
          };
          
          ai-blueprints = flake-utils.lib.mkApp {
            drv = ai-blueprint-generator;
          };
        };
        
        # 📦 Hydra Jobs
        hydraJobs = {
          galactic-infrastructure = galactic-infrastructure;
          markdown-wiki = markdown-wiki;
          ai-blueprint-generator = ai-blueprint-generator;
          container = container;
        };
      });
  
  # 🎊 Metadata
  meta = {
    description = "Post-Industrial Ecological Civilization Documentation System";
    longDescription = ''
      A revolutionary approach to civilization infrastructure where documentation 
      becomes executable code, builds are pure functions, and federal funding 
      creates post-scarcity agriculture.
      
      Features:
      - ClojureScript DSL for living documentation
      - Markdown-to-wiki system with beautiful Svelte components
      - Dual build architecture (localhost + GitHub Pages)
      - Nix-based reproducible builds
      - AI blueprint generation (Cursor, ChatGPT, Claude)
      - Modern Monetary Theory integration
      - Post-industrial ecological civilization vision
    '';
    homepage = "https://github.com/foolsgoldtoshi-star/highvalleywake-pbc-galactic";
    license = "The Unlicense";
    maintainers = [ "foolsgoldtoshi-star" ];
    platforms = [ "x86_64-linux" "aarch64-linux" "x86_64-darwin" "aarch64-darwin" ];
  };
}
