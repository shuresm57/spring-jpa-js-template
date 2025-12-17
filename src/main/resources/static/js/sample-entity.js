// Sample entity management functions
class SampleEntityManager {
    constructor() {
        this.entities = [];
        this.init();
    }

    async init() {
        await this.loadEntities();
        this.renderEntities();
        this.bindEvents();
    }

    async loadEntities() {
        try {
            this.entities = await sampleEntityAPI.getAll();
        } catch (error) {
            console.error('Failed to load entities:', error);
        }
    }

    renderEntities() {
        const container = document.getElementById('entities-container');
        if (!container) return;

        container.innerHTML = this.entities.map(entity => `
            <div class="bg-gray-800 p-4 rounded-lg border border-gray-700">
                <h3 class="text-lg font-semibold text-white mb-2">${entity.name}</h3>
                <p class="text-gray-300 mb-2">${entity.description || 'No description'}</p>
                <div class="flex justify-between items-center">
                    <span class="text-sm text-gray-500">ID: ${entity.id}</span>
                    <div class="space-x-2">
                        <button onclick="entityManager.editEntity(${entity.id})" 
                                class="bg-blue-600 hover:bg-blue-700 px-3 py-1 rounded text-sm">
                            Edit
                        </button>
                        <button onclick="entityManager.deleteEntity(${entity.id})" 
                                class="bg-red-600 hover:bg-red-700 px-3 py-1 rounded text-sm">
                            Delete
                        </button>
                    </div>
                </div>
            </div>
        `).join('');
    }

    bindEvents() {
        const form = document.getElementById('entity-form');
        if (form) {
            form.addEventListener('submit', (e) => this.handleSubmit(e));
        }
    }

    async handleSubmit(e) {
        e.preventDefault();
        const formData = new FormData(e.target);
        const entity = {
            name: formData.get('name'),
            description: formData.get('description')
        };

        try {
            await sampleEntityAPI.create(entity);
            await this.loadEntities();
            this.renderEntities();
            e.target.reset();
        } catch (error) {
            console.error('Failed to create entity:', error);
        }
    }

    async deleteEntity(id) {
        if (confirm('Are you sure you want to delete this entity?')) {
            try {
                await sampleEntityAPI.delete(id);
                await this.loadEntities();
                this.renderEntities();
            } catch (error) {
                console.error('Failed to delete entity:', error);
            }
        }
    }

    editEntity(id) {
        const entity = this.entities.find(e => e.id === id);
        if (entity) {
            document.getElementById('name').value = entity.name;
            document.getElementById('description').value = entity.description || '';
        }
    }
}

// Initialize when DOM is loaded
document.addEventListener('DOMContentLoaded', () => {
    window.entityManager = new SampleEntityManager();
});