<template>
  <p @click="sendClick" :class="{default: state === 'default', active: state === 'active', done: state === 'done'}" class="default" :id="number">
      {{number}}
  </p>
</template>

<script>
import {ref } from 'vue'
  export default{
    props: {
      state: {
        default: 'default',
        type: String,
      },
      number: String,
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
    @apply w-7 h-7 flex items-center justify-center font-bold rounded-full border border-gray-400 text-gray-400 cursor-pointer;
  }

  .active{
    @apply w-7 h-7 flex items-center justify-center font-bold rounded-full border border-gray-400 text-white bg-white bg-opacity-50 cursor-pointer;
  }
  .done{
    @apply w-7 h-7 flex items-center justify-center font-bold rounded-full border border-gray-400 text-gray-400 opacity-70 line-through cursor-pointer;
  }
</style>

