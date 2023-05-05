///<reference types="Cypress"/> 

// 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
// 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

describe('GitHub API', () => {
    var baseUrl = "https://api.github.com"
    var repoName ;
    var owner = "1803amar";
    var token = "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";
    var id;


    it('should create a repository for an authenticated user', () => {
      
    
      cy.request({
        method: 'POST',
        url: baseUrl + "/user/repos",
        headers: {
            //by default the response will be in json format
            Authorization: "Bearer " + token,
            accept: "application/json",
          },
        body: {
            name: "Project-abc7",
            description: "Here's github repo is created",
          },
      }).then((response) => {
        expect(response.status).to.eq(201);
        
        repoName = response.body.name;
        
        cy.log(repoName);
      });
    });

    it('should create an autolink reference for a repository', () => {
       
        
        cy.request({
          method: 'POST',
          url: `${baseUrl}/repos/${owner}/${repoName}/autolinks`,
          headers: {
            //by default the response will be in json format
            Authorization: "Bearer " + token,
            accept: "application/json",
          },
        body:{
            key_prefix:"TICKETabcd-",
        url_template:"https://example.com/TICKETabcd?query=<num>",
        is_alphanumeric:true
    }
        }).then((response) => {
          expect(response.status).to.eq(201);
          id = response.body.id;
          cy.log(JSON.stringify(response.body));
        
        });
      });

      
    
      it('should get all repository topics', () => {
        
        cy.request({
          method: 'GET',
          url: `${baseUrl}/repos/${owner}/${repoName}/topics`,
          headers: {
            //by default the response will be in json format
            Authorization: "Bearer " + token,
            accept: "application/json",
          },
        }).then((response) => {
          expect(response.status).to.eq(200);
       
        });
      });
    
      it('should get a valid autolink reference', () => {
        cy.request({
          method: 'GET',
          url: `${baseUrl}/repos/${owner}/${repoName}/autolinks/${id}`,
          headers: {
            //by default the response will be in json format
            Authorization: "Bearer " + token,
            accept: "application/json",
          },
        }).then((response) => {
          expect(response.status).to.eq(200)
       
        })
      });

      it('should delete a valid autolink reference', () => {
        cy.request({
          method: 'DELETE',
          url: `${baseUrl}/repos/${owner}/${repoName}/autolinks/${id}`,
          headers: {
            //by default the response will be in json format
            Authorization: "Bearer " + token,
            accept: "application/json",
          },
        }).then((response) => {
          expect(response.status).to.eq(204)
       
        })
      });
    
    
      it('should get a repository', () => {
       
        cy.request({
          method: 'GET',
          url: `${baseUrl}/repos/${owner}/${repoName}`,
          headers: {
            //by default the response will be in json format
            Authorization: "Bearer " + token,
            accept: "application/json",
          },
        }).then((response) => {
          expect(response.status).to.eq(200);
        
        });
      });
    
      it('should replace all repository topics', () => {
       
        const body = {
          names: ['topic1', 'topic2']
        };
        cy.request({
          method: 'PUT',
          url: `${baseUrl}/repos/${owner}/${repoName}/topics`,
          headers: {
            //by default the response will be in json format
            Authorization: "Bearer " + token,
            accept: "application/json",
          },
          body
        }).then((response) => {
          expect(response.status).to.eq(200);
          expect(response.body.names).to.be.an('array');
          expect(response.body.names).to.have.lengthOf(2);
          expect(response.body.names).to.eql(body.names);
          cy.log(`Topics for repository ${owner}/${repoName} replaced with ${body.names.join(', ')}`);
        });
      });

      it('should delete a repository', () => {
        cy.request({
          method: 'DELETE',
          url:  `${baseUrl}/repos/${owner}/${repoName}`,
          headers: {
            //by default the response will be in json format
            Authorization: "Bearer " + token,
            accept: "application/json",
          },
        }).then((response) => {
          expect(response.status).to.eq(204)
      
        })
      });

  });