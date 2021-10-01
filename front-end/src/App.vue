<template>
    <main class="grid h-auto min-h-screen main-bg place-items-center">
              
        <!------ Login Form ----->
        <router-view/>

        
    </main>
</template>

<script>
import Home from './views/Home.vue';
import axios from 'axios';
import {gsap} from 'gsap';
import { onMounted } from '@vue/runtime-core';
    export default {
        components: {Home},

        setup(){
            onMounted (() => {
                
            })
        }
    }

</script>

<style scoped>
    .main-bg{
        background: linear-gradient(270deg, #FF9A9E 0%, #FAD0C4 100%);
    }
    
   
</style>