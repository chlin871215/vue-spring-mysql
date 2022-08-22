<template>
  <div class="submit-form">
    <div v-if="!submitted">
      <div class="form-group">
        <label for="id">Id</label>
        <input
          type="text"
          class="form-control"
          id="id"
          required
          v-model="user.id"
          name="id"
        />
      </div>

      <div class="form-group">
        <label for="name">Name</label>
        <input
          class="form-control"
          id="name"
          required
          v-model="user.name"
          name="name"
        />
      </div>

      <div class="form-group">
        <label for="age">Age</label>
        <input
          type="text"
          class="form-control"
          id="age"
          required
          v-model="user.age"
          name="age"
        />
      </div>

      

      <button @click="saveUser" class="btn btn-success">Submit</button>
    </div>

    <div v-else>
      <h4>Submitted successfully!</h4>
      <button class="btn btn-success" @click="newUser">Add</button>
    </div>
  </div>
</template>

<script>
import UserDataService from "../services/UserDataService";

export default {
  name: "add-tutorial",
  data() {
    return {
      user: {
        id:"",
        name:"",
        age:""
      },
      submitted: false
    };
  },
  methods: {
    saveUser() {
      var data = {
        id: this.user.id,
        name:this.user.name,
        age: this.user.age
      };

      UserDataService.create(data)
        .then(response => {
          this.user.id = response.data.id;
          console.log(response.data);
          this.submitted = true;
        })
        .catch(e => {
          console.log(e);
        });
    },
    
    newUser() {
      this.submitted = false;
      this.user = {};
    }
  }
};
</script>

<style>
.submit-form {
  max-width: 300px;
  margin: auto;
}
</style>
