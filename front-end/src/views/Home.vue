<template>
  <div v-if="!isLogin" class="absolute z-10 flex flex-col items-center w-7/12 from-gray-300 pb-14 text-gray-50 rounded-xl bg-gradient-to-br to-gray-400 pt-9 form-container">
    <h1 class="mb-10 text-5xl font-bold">Round 1 Quiz</h1>
    <p v-if="error" class="font-semibold text-red-700">{{error}}</p>
    <form class="w-1/2">
      <div class='mb-8'>
        <label for="group" class='text-xl font-semibold '>Group Name:</label>
        <input class="block w-full px-8 py-4 mt-2 bg-white rounded-full" id="group" type="text" placeholder="group123" v-model ="groupName">
      </div>
      <div  class='mb-12'>
        <label for="code" class='text-xl font-semibold '>Quiz Code:</label>
        <input class="block w-full px-8 py-4 mt-2 bg-white rounded-full" type="text" placeholder='quiz01' v-model="quizCode"></div>

        
    </form>
    <a href="#" class="inline-block py-3 text-2xl font-bold rounded-full px-7 bg-gradient-to-r from-red-600 to-pink-600" :class="{loading: isLoading}" @click="authenticate"> <div v-if="isLoading" class="inline-block w-4 h-4 ease-linear border-2 border-t-2 border-gray-200 rounded-full loader"></div> Start the quiz!</a>
  </div>
  
  <MainQuiz :groupName="groupName" :quizCode="quizCode"  v-else/>
</template>

<script >
import {ref, computed } from 'vue'
import LoginAPI from '../services/LoginAPI'
import MainQuiz from '../components/MainQuiz.vue'
export default {
  components: {MainQuiz},
  
  setup() {
    const isLogin = ref(false);
    const isLoading = ref(false);
    const groupName = ref(null);
    const quizCode = ref(null);
    const error = ref(null);
    const authenticate = () => {
     if(!groupName.value) {
        
       error.value= 'Group name is required!';
    }
    else if (!quizCode.value) {
      error.value= 'Quiz code is required!';
    }
    else{
      isLoading.value = true;
      LoginAPI.auth({groupName:groupName.value, quizCode: quizCode.value})
      .then((response) => {
        if (response.data.success) {
          isLogin.value = true;
        }
        isLoading.value = false;
      }).catch(err => {error.value = err.response.data.errors[0].message; isLoading.value = false});
    }
    }

    return {authenticate, groupName, quizCode, error, isLogin, isLoading}
  }

}

</script>

<style scoped>
input::placeholder {
  font-size: 16px;

}
.loading{
  @apply opacity-50;
}

input{
  @apply text-gray-700;
}

.form-container{
  backdrop-filter: blur(48px);
}

.loader {
  border-top-color: #3498db;
  -webkit-animation: spinner 1.5s linear infinite;
  animation: spinner 1.5s linear infinite;
}

@-webkit-keyframes spinner {
  0% { -webkit-transform: rotate(0deg); }
  100% { -webkit-transform: rotate(360deg); }
}

@keyframes spinner {
  0% { transform: rotate(0deg); }
  100% { transform: rotate(360deg); }
}
</style>
