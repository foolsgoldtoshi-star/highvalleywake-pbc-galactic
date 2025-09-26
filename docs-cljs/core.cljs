(ns docs.core
  (:require [clojure.string :as str]))

;; 🌌 ClojureScript Documentation DSL
;; Philosophy: Documentation as executable data structures
;; Rich Hickey meets Helen Atthowe meets Modern Monetary Theory

(defn render-header
  "Render documentation header with emoji styling"
  [{:keys [level title emoji]}]
  (let [tag (str "h" level)
        header-class (str "header-level-" level)]
    (str "<" tag " class=\"" header-class "\">"
         (when emoji (str emoji " "))
         title
         "</" tag ">\n")))

(defn render-section
  "Render documentation section with beautiful styling"
  [{:keys [header content]}]
  (str (render-header header)
       "<div class=\"section-content\">\n"
       (if (string? content)
         (str "<p>" content "</p>\n")
         (->> content
              (map #(if (string? %)
                      (str "<p>" % "</p>\n")
                      (str "<div class=\"data-structure\">"
                           "<pre><code>" (pr-str %) "</code></pre>"
                           "</div>\n")))
              (str/join "")))
       "</div>\n\n"))

(defn render-mmt-framework
  "Render Modern Monetary Theory integration framework"
  [{:keys [economists agencies blockchain logistics]}]
  (str "<div class=\"mmt-framework\">\n"
       "<h3>💰 Modern Monetary Theory Foundation</h3>\n"
       
       "<div class=\"economists\">\n"
       "<h4>🎓 Economic Philosophers:</h4>\n"
       "<ul>\n"
       (->> economists
            (map #(str "<li><strong>" % "</strong></li>\n"))
            (str/join ""))
       "</ul>\n"
       "</div>\n\n"
       
       "<div class=\"federal-agencies\">\n"
       "<h4>🏛️ Federal Support:</h4>\n"
       "<ul>\n"
       (->> agencies
            (map (fn [[agency desc]]
                   (str "<li><strong>" (name agency) "</strong>: " desc "</li>\n")))
            (str/join ""))
       "</ul>\n"
       "</div>\n\n"
       
       "<div class=\"blockchain-infrastructure\">\n"
       "<h4>⛓️ Blockchain Infrastructure:</h4>\n"
       "<ul>\n"
       (->> blockchain
            (map (fn [[chain desc]]
                   (str "<li><strong>" (name chain) "</strong>: " desc "</li>\n")))
            (str/join ""))
       "</ul>\n"
       "</div>\n\n"
       
       "</div>\n"))

(defn render-timeline
  "Render galactic civilization timeline"
  [phases]
  (str "<div class=\"galactic-timeline\">\n"
       "<h3>🌌 Galactic Evolution Timeline</h3>\n"
       "<table class=\"timeline-table\">\n"
       "<thead>\n"
       "<tr><th>Phase</th><th>Timeline</th><th>MMT Support</th><th>Status</th></tr>\n"
       "</thead>\n"
       "<tbody>\n"
       (->> phases
            (map (fn [{:keys [name timeline mmt-support status]}]
                   (let [status-emoji (case status
                                        :completed "✅"
                                        :in-progress "🔄"
                                        :planned "📋"
                                        "🌱")]
                     (str "<tr>\n"
                          "<td><strong>" name "</strong></td>\n"
                          "<td>" timeline "</td>\n" 
                          "<td>" mmt-support "</td>\n"
                          "<td>" status-emoji " " (name status) "</td>\n"
                          "</tr>\n"))))
            (str/join ""))
       "</tbody>\n"
       "</table>\n"
       "</div>\n"))

(defn document
  "Main documentation DSL function - generates beautiful HTML"
  [{:keys [title subtitle philosophy sections mmt-framework timeline]}]
  (str 
    ;; Document header
    "<div class=\"galactic-document\">\n"
    "<div class=\"document-header\">\n"
    "<h1 class=\"document-title\">" title "</h1>\n"
    (when subtitle
      (str "<div class=\"document-subtitle\">" subtitle "</div>\n"))
    (when philosophy
      (str "<div class=\"philosophy-badge\">🌱 \"" philosophy "\"</div>\n"))
    "</div>\n\n"
    
    ;; MMT framework (if provided)
    (when mmt-framework
      (render-mmt-framework mmt-framework))
    
    ;; Main content sections
    "<div class=\"document-content\">\n"
    (->> sections
         (map render-section)
         (str/join "\n"))
    "</div>\n\n"
    
    ;; Timeline (if provided)
    (when timeline
      (render-timeline timeline))
    
    ;; Footer
    "<div class=\"document-footer\">\n"
    "<div class=\"build-badge\">🌽 Built with functional programming by foolsgoldtoshi-star 🖤🤎💙</div>\n"
    "<div class=\"philosophy-note\">\n"
    "<em>\"Rich Hickey meets Helen Atthowe meets NixOS meets Modern Monetary Theory\"</em>\n"
    "</div>\n"
    "</div>\n"
    "</div>\n"))

(defn veganic-document
  "Specialized documentation for veganic agriculture"
  [doc-data]
  (-> doc-data
      (assoc-in [:philosophy] "Simple made easy, farms made functional")
      document))

(defn mmt-document
  "Specialized documentation for MMT integration"
  [doc-data]
  (-> doc-data
      (assoc-in [:philosophy] "Federal sovereignty for ecological transition")
      document))

(defn galactic-document
  "Specialized documentation for galactic civilization"
  [doc-data]
  (-> doc-data
      (assoc-in [:philosophy] "Post-industrial ecological civilization through code")
      document))

;; 🎊 Export main functions
(def ^:export document document)
(def ^:export veganic-document veganic-document)
(def ^:export mmt-document mmt-document)
(def ^:export galactic-document galactic-document)

;; 🌱 ClojureScript Documentation DSL Engine
;; From epistemic seed bank to galactic starcommons
;; Built with Rich Hickey's philosophy for post-industrial civilization
