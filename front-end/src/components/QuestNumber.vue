<template>
  <p @click="sendClick" class="flex items-center justify-center w-10 h-10 font-bold text-gray-700 border-2 border-gray-700 rounded-full cursor-pointer lg:w-9 lg:h-9" :class="{active: isActive, done: isDone && !isActive}" :id="number">
      {{number}}
  </p>
</template>

<script>
import {ref } from 'vue'
  export default{
    props: {
      isDone: Boolean,
      isActive: Boolean,
      number: Number,
    },

    setup(props,{emit}){
      const sendClick = (event) => {
            emit("clickQuestion", event.target);
        }
    
      return {sendClick}
    }
}

</script>

