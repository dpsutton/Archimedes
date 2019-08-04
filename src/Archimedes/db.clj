(ns Archimedes.db
  (:refer-clojure :exclude [== indexed?])
  (:require [clojure.core.logic :refer :all :as l]
            [clojure.core.logic.pldb :as pldb]))

(pldb/db-rel
  types-
  ^:indexed
  type-name
  ^:indexed
  representation
  ^:indexed
  natural-or-other
  ^:indexed
  width
  ^:indexed
  point
  ^:indexed
  boxed-or-unboxed
  ^:indexed
  return-or-no-return)

(pldb/db-rel operations-
  ^:index op-name
  ^:index arity
  ^:index return)


;; contaminates
(def type-db
  {:long {:representation :primitive
          :natural-or-other :natural
          :width :fixed
          :point :none
          :boxed-or-unboxed :unboxed
          :return-or-no-return :return}
   :double {:representation :primitive
            :natural-or-other :other
            :width :fixed
            :point :floating
            :boxed-or-unboxed :unboxed
            :return-or-no-return :return}
   :Long {:representation :object
          :natural-or-other :natural
          :width :fixed
          :point :none
          :boxed-or-unboxed :boxed
          :return-or-no-return :return}
   :Double {:representation :object
            :natural-or-other :other
            :width :fixed
            :point :floating
            :boxed-or-unboxed :boxed
            :return-or-no-return :return}
   :float {:representation :primitive
           :natural-or-other :other
           :width :fixed
           :point :floating
           :boxed-or-unboxed :unboxed
           :return-or-no-return :no-return}
   :Float {:representation :object
           :natural-or-other :other
           :width :fixed
           :point :floating
           :boxed-or-unboxed :boxed
           :return-or-no-return :return}
   :int {:representation :primitive
         :natural-or-other :natural
         :width :fixed
         :point :none
         :boxed-or-unboxed :unboxed
         :return-or-no-return :no-return}
   :Integer {:representation :object
             :natural-or-other :natural
             :width :fixed
             :point :none
             :boxed-or-unboxed :boxed
             :return-or-no-return :return}
   :Ratio {:representation :object
           :natural-or-other :other
           :width :open
           :point :none
           :boxed-or-unboxed :unboxed
           :return-or-no-return :return}
   :Number {:representation :object
            :natural-or-other :other
            :width :open
            :point :none
            :boxed-or-unboxed :unboxed
            :return-or-no-return :return}
   :Object {:representation :object
            :natural-or-other :other
            :width :open
            :point :none
            :boxed-or-unboxed :unboxed
            :return-or-no-return :no-return}
   :BigDecimal {:representation :object
                :natural-or-other :other
                :width :open
                :point :floating
                :boxed-or-unboxed :unboxed
                :return-or-no-return :return}
   :BigInteger {:representation :object
                :natural-or-other :natural
                :width :open
                :point :none
                :boxed-or-unboxed :unboxed
                :return-or-no-return :no-return}
   :BigInt {:representation :object
            :natural-or-other :natural
            :width :open
            :point :none
            :boxed-or-unboxed :unboxed
            :return-or-no-return :return}})

(def operations
  {:add {:arity 2}
   :subtract {:arity 2}
   :zero? {:arity 1
           :return :boolean}
   :neg {:arity 1}
   :divide {:arity 2}
   :multiply {:arity 2}
   :quotient {:arity 2}
   :remainder {:arity 2}
   ;; :equiv {:arity 2}
   ;; :lt {:arity 2}
   ;; :lte {:arity 2}
   ;; :gt {:arity 2}
   ;; :gte {:arity 2}
   })

(def types-ldb
  (apply pldb/db
         (for [[type-name data] type-db]
           [types-
            type-name
            (:representation data)
            (:natural-or-other data)
            (:width data)
            (:point data)
            (:boxed-or-unboxed data)
            (:return-or-no-return data)])))

(def operations-ldb
  (apply pldb/db
         (for [[op-name data] operations]
           [operations- op-name (:arity data) (:return data)])))

(def natural-division-result :Ratio)
