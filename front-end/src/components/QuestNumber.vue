<template>
  <p @click="sendClick" class="flex items-center justify-center font-bold text-gray-700 border-2 border-gray-700 rounded-full cursor-pointer w-9 h-9" :class="{active: isActive, done: isDone && !isActive}" :id="number">
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
<style scoped>
  .default{
    @apply w-9 h-9 flex items-center justify-center font-bold rounded-full border-2 border-gray-700 text-gray-700 cursor-pointer;
  }

  .active{
    @apply w-9 h-9 flex items-center justify-center font-bold rounded-full border-none bg-error-900 text-white  cursor-pointer;
  }
  .done{
    @apply w-9 h-9 flex items-center justify-center font-bold rounded-full border-2 border-gray-700 text-gray-700 opacity-50 line-through cursor-pointer;
  }
</style>

