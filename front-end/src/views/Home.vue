<template>
  <div v-if="!isLogin" class="absolute z-10 flex flex-col items-center w-7/12 from-gray-300 pb-14 text-gray-50 rounded-xl bg-gradient-to-br to-gray-400 pt-9 form-container">
    <h1 class="mb-10 text-5xl font-bold">Round 1 Quiz</h1>
    <form class="w-1/2">
      <div class='mb-8'>
        <label for="group" class='text-xl font-semibold '>Group Name:</label>
        <input class="block w-full px-8 py-4 mt-2 bg-white rounded-full" id="group" type="text" placeholder="group123">
      </div>
      <div  class='mb-12'>
        <label for="code" class='text-xl font-semibold '>Quiz Code:</label>
        <input class="block w-full px-8 py-4 mt-2 bg-white rounded-full" type="text" placeholder='quiz01'></div>

        
    </form>
    <a href="" class="inline-block py-3 text-2xl font-bold rounded-full px-7 bg-gradient-to-r from-red-600 to-pink-600 ">Start the quiz!</a>
  </div>
  
  <MainQuiz v-else/>
</template>

<script >
import {ref } from 'vue'
import MainQuiz from '../components/MainQuiz.vue'
export default {
  components: {MainQuiz},
  data: () =>{
    return {isLogin: true}
  },
  setup() {
    
  }

}

</script>

<style scoped>
input::placeholder {
  font-size: 16px;

}

input{
  @apply text-gray-700;
}

.form-container{
  backdrop-filter: blur(48px);
}
</style>
